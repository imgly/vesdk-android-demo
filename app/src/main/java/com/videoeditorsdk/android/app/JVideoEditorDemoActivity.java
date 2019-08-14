package com.videoeditorsdk.android.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import ly.img.android.pesdk.VideoEditorSettingsList;
import ly.img.android.pesdk.assets.filter.basic.FilterPackBasic;
import ly.img.android.pesdk.assets.font.basic.FontPackBasic;
import ly.img.android.pesdk.assets.frame.basic.FramePackBasic;
import ly.img.android.pesdk.assets.overlay.basic.OverlayPackBasic;
import ly.img.android.pesdk.assets.sticker.emoticons.StickerPackEmoticons;
import ly.img.android.pesdk.assets.sticker.shapes.StickerPackShapes;
import ly.img.android.pesdk.backend.model.constant.Directory;
import ly.img.android.pesdk.backend.model.state.LoadSettings;
import ly.img.android.pesdk.backend.model.state.SaveSettings;
import ly.img.android.pesdk.backend.model.state.manager.SettingsList;
import ly.img.android.pesdk.ui.activity.ImgLyIntent;
import ly.img.android.pesdk.ui.activity.VideoEditorBuilder;
import ly.img.android.pesdk.ui.model.state.*;
import ly.img.android.pesdk.ui.utils.PermissionRequest;
import ly.img.android.serializer._3._0._0.PESDKFileWriter;

import java.io.File;
import java.io.IOException;

public class JVideoEditorDemoActivity extends Activity implements PermissionRequest.Response {

    // Important permission request for Android 6.0 and above, don't forget to add this!
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void permissionGranted() {}

    @Override
    public void permissionDenied() {
        /* TODO: The Permission was rejected by the user. The Editor was not opened,
         * Show a hint to the user and try again. */
    }

    public static int VESDK_RESULT = 1;
    public static int GALLERY_RESULT = 2;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private VideoEditorSettingsList createVesdkSettingsList() {
        // Create a empty new SettingsList and apply the changes on this referance.
        VideoEditorSettingsList settingsList = new VideoEditorSettingsList();

        // If you include our asset Packs and you use our UI you also need to add them to the UI,
        // otherwise they are only available for the backend
        // See the specific feature sections of our guides if you want to know how to add our own Assets.

        settingsList.getSettingsModel(UiConfigFilter.class).setFilterList(
                FilterPackBasic.getFilterPack()
        );

        settingsList.getSettingsModel(UiConfigText.class).setFontList(
                FontPackBasic.getFontPack()
        );

        settingsList.getSettingsModel(UiConfigFrame.class).setFrameList(
                FramePackBasic.getFramePack()
        );

        settingsList.getSettingsModel(UiConfigOverlay.class).setOverlayList(
                OverlayPackBasic.getOverlayPack()
        );

        settingsList.getSettingsModel(UiConfigSticker.class).setStickerLists(
                StickerPackEmoticons.getStickerCategory(),
                StickerPackShapes.getStickerCategory()
        );

        // Set custom editor image export settings
        settingsList.getSettingsModel(SaveSettings.class)
                .setExportDir(Directory.DCIM, "SomeFolderName")
                .setExportPrefix("result_")
                .setSavePolicy(SaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT);

        return settingsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openGallery = findViewById(R.id.openGallery);
        openGallery.setOnClickListener(v -> openSystemGalleryToSelectAnVideo());
    }

    private void openSystemGalleryToSelectAnVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,"video/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, GALLERY_RESULT);
        } else {
            Toast.makeText(
                    this,
                    "No Gallery APP installed",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void openEditor(Uri inputImage) {
        VideoEditorSettingsList settingsList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            settingsList = createVesdkSettingsList();
        } else {
            Toast.makeText(this, "Video support needs Android 4.3", Toast.LENGTH_LONG).show();
            return;
        }

        // Set input image
        settingsList.getSettingsModel(LoadSettings.class).setSource(inputImage);

        new VideoEditorBuilder(this)
                .setSettingsList(settingsList)
                .startActivityForResult(this, VESDK_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_RESULT) {
            // Open Editor with some uri in this case with an image selected from the system gallery.
            Uri selectedImage = data.getData();
            openEditor(selectedImage);

        } else if (resultCode == RESULT_OK && requestCode == VESDK_RESULT) {
            // Editor has saved an Image.
            Uri resultURI = data.getParcelableExtra(ImgLyIntent.RESULT_IMAGE_URI);
            Uri sourceURI = data.getParcelableExtra(ImgLyIntent.SOURCE_IMAGE_URI);

            // Scan result uri to show it up in the Gallery
            if (resultURI != null) {
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(resultURI));
            }

            // Scan source uri to show it up in the Gallery
            if (sourceURI != null) {
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(sourceURI));
            }

            Log.i("PESDK", "Source image is located here " + sourceURI);
            Log.i("PESDK", "Result image is located here " + resultURI);

            // TODO: Do something with the result image

            // OPTIONAL: read the latest state to save it as a serialisation
            SettingsList lastState = data.getParcelableExtra(ImgLyIntent.SETTINGS_LIST);
            try {
                new PESDKFileWriter(lastState).writeJson(new File(
                        Environment.getExternalStorageDirectory(),
                        "serialisationReadyToReadWithPESDKFileReader.json"
                ));
            } catch (IOException e) { e.printStackTrace(); }

        } else if (resultCode == RESULT_CANCELED && requestCode == VESDK_RESULT) {
            // Editor was canceled
            Uri sourceURI = data.getParcelableExtra(ImgLyIntent.SOURCE_IMAGE_URI);
            // TODO: Do something with the source...
        }
    }
}
