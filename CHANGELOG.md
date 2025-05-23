# VideoEditor SDK - Changelog
## v10.10.3

### Fixed
* Long loading times appear on loading images on some devices, due to undocumented changes in API 35.

## v10.10.2

### Fixed
* Under certain circumstances, such as using `minSdkVersion` 31 together with third-party libraries such as "dagger", the editor may crash with a recursive clone loop. This is due to a change in the implementation of the clone method in the Android SDK. 

## v10.10.1

### Fixed
* Editor sometimes crashes when the Editor is closed before the engine is initalised.
* Video is exported even when there are no changes and ForceTrim is enabled

## v10.10.0

### Fixed
Android-15-16KB
* Added ability to set video export size limit using `VideoEditorSaveSettings.exportSizeLimit(value: ImageSize)`
  * 🚨 The default video export resolution limit is now 4k (3840x2160px) or (2160x3840px) depending on the orientation.
  * We limit to 4k by default, because some Samsung devices like the German version of the S22, S22 Ultra, S23 and S23 Ultra support 8k export while others crash because of a driver issue.
  * It's because Samsung uses different chipsets, with different drivers, for the same device in different regions.
* Potential FileNotFoundException because file name too long, when saveUriPermissions is true.
* Typo in Archivo font identifier to `imgly_font_archivo_black`.
* Potential FileNotFoundException because file name too long, when saveUriPermissions is true.
* Serializations exported by WEB with tintMode set to "none", loaded as tintMode "solid".
* Edge to Edge support for Android 15 and lower 
* The background service stayed alive after the export was canceled or finished.
* Potential `FileNotFoundException` caused by file names being too long when `saveUriPermissions` is true.
* Serializations is exported by WEB with `tintMode` set to "none" were loaded as `tintMode` "solid".

## Improved
* Support for Android 15 16KB page size
* Support for Android API 35 target.

### Changed
* Thanks to Google, we can now use the new `FOREGROUND_SERVICE_MEDIA_PROCESSING` permission, and they have enabled it for older Android versions as well.
  * 🚨Permission `FOREGROUND_SERVICE_MEDIA_PLAYBACK` is no longer required.
* Added the ability to set the video export size limit using `VideoEditorSaveSettings.exportSizeLimit(value: ImageSize)`.
  * 🚨The default video export resolution limit is now 4K (3840x2160px) or (2160x3840px), depending on the orientation.
  * We limit to 4K by default because some Samsung devices, like the German versions of the S22, S22 Ultra, S23, and S23 Ultra, support 8K export while others crash due to a driver issue.
  * This is because Samsung uses different chipsets with different drivers for the same device in different regions.
* A typo in the Archivo font identifier. We changed to `imgly_font_archivo_black`, while keeping the old identifier for backward compatibility.
* To support Android 15 we upgraded APG to the minimum Version 8.4.0.
* Aligning with the Android 15 16 KB size libraries we upgraded to the lowest working Kotlin Version 1.8.10.
* [APG/GRADLE-PLUGIN] Upgraded to '8.4.0'
* [KSP-PLUGIN] Upgraded to '1.8.10-1.0.9'
* [Kotlin-PLUGIN] Upgraded to '1.8.10'
* [android-gif-drawable] Upgraded to '1.2.29'
* [Androidx-Dependencies] Upgraded camerax_version to '1.4.0-rc01', fragment to '1.8.2', appcompat to '1.7.0', vectordrawable to '1.2.0', annotation to '1.8.2'
* [Google.mlkit-Dependency] Upgraded segmentation-selfie to '16.0.0-beta6'

## v10.9.0

### Fixed
* [KSP-PLUGIN] Event-System is not working correctly in some cases, when using KSP Plugin.
* [GRADLE-PLUGIN] KSP Plugin does not work on newer Gradle versions.
* [GRADLE-PLUGIN] KAPT and KSP Plugin detection is not working correctly.
* Images and icons occasionally fail to load.
* Performance degradation during the loading of numerous images.

### Improved
* Better runtime and build-time error messages for configuration issues.
* New Syntax for IMG.LY plugin configuration. Please use `IMGLY.configure { }` instead of `imglyConfig { }`, you will receive errors when you are using the new syntax the wrong way.

### Breaking Changes
* 🚨 [GRADLE-PLUGIN] Significant modifications have been implemented that will affect a minor segment of our customer base. Should your build process encounter errors, it is recommended to consult the build output for detailed information.

## v10.8.2

### Fixed
* License check does not work when using KSP plugin.

## v10.8.1

### Known-Issues
* __KSP plugin is broken. Please update to 10.8.2.__

### Fixed
* [VideoEditorSDK] Video export was limited by the frame rate limiter.
* [VideoEditorSDK] When the video source changes rapidly, there may be issues with the IMGLYFileReader not waiting properly. This can lead to unexpected outcomes, such as trim start and end times not being accurate.
* [VideoEditorSDK] `ArithmeticException` in `VideoCompositionSettings` when source is changed.
* [PhotoEditorSDK] Exporting images on the Pixel 8 (Pro) and maybe other devices, result in an endless export spinner.
* `AcceptTextButton` not visible for un-cancellable tool panels.
* Build issues with Android 14
* Camera is not working on some phones.
* Camera preview aspect ratio is not the same as the captured image.
* When only one line is drawn, the brush tool might be deserialized with the wrong size after the source is changed.
* If the image is smaller than the display resolution, the accuracy of the pipette's source position may be compromised.
* Crash in the TextDesign and Sticker tool when the user clicks too fast in the menu.
* ConfigLoader (React Native, Cordova/Ionic)
  * `personalVideoClips` is not disabled correctly if the video library is not used.
* Kotlin 1.9.0+ compatibility issues, by using KSP.

### Changed
* 🚨 Minimum compileSdkVersion version of the `ui:camera` and `backend:camera` module is now 33
* 🚨 Replaced font 'Galano' with font 'Lexend Bold', 'Campton' with 'Outfit Bold', 'Wolesbro' with 'Cookie', and 'Amberlight' with 'Allison'.
* KSP (Kotlin Symbol Processing) can now be activated as an alternative to KAPT (Kotlin Annotation Processing Tool) for annotation processing.
  * 🚨 This feature is automatically enabled when you add the KSP plugin into your project.
  * If auto-detection fails, you can manually enable it using the following configuration: `imglyConifg { useKspProcessor = true }`

## ~~v10.8.0~~ (Broken release, don't use it!)
* This version was recalled due to an error

## v10.7.3

### Fixed
* [VideoEditorSDK] Crop header information in videos is ignored. This can also cause artifacts.
* [VideoEditorSDK] Some unsupported video formats are not detected as unsupported.
  [VideoEditorSDK] Video trim does not work correctly when using background rendering.
* [VideoEditorSDK] Soundstripe styles are broken for non default themes.
* [VideoEditorSDK] Minimal video duration is not respected when between 500 and 1000 milliseconds. 
* Crop area minimal size is too high, to crop screenshots.
* `DocumentRenderWorker` is not canceled when `WorkManager.cancelWorkById` is called.
* Missing `FileDescriptor` closes, resulting in a resource leak.
* Layer History is not working, when using R8 Full Mode.
* Soundstripe is not loading, when using R8 Full Mode.
* Giphy is not loading, when using R8 Full Mode.

### Improved
* [VideoEditorSDK] Videos with mime type `video/dolby-vision` are now decoded with a fallback decoder. This may work on some devices, that do not officially support this format.

## v10.7.2

### Fixed
* Soundstripe module compile issues.

## v10.7.1

### Fixed
* Background rendering not getting completed when rendering videos with big files.
* IOExceptions when cached files are deleted.
* Trim is reset when loading/changing the source. This can cause an issue when loading a serialization with a trim.
* Brush is sometimes not at the correct position after switching source.
* A rare crash can occur while opening the StickerOptionToolPanel due to improper initialization in the event handlers of the StickerOptionToolPanel.
* Audio level don't reset to zero after deleting the overlay.

### Improved
* Event handlers for initialization now have a specific call order to ensure consistent handling of all events.
* Added `UiConfigComposition.addVideoIntent` to change the intent used to add videos to the composition.
* Added `DocumentRenderWorker.RESULT_URI_KEY` to `DocumentRenderWorker` result. It's a string due to the fact that `Uri` is not serializable.

## v10.7.0

### Added
* If `uiMainConfig.showConfirmCloseDialog` is `false` no discard confirm dialog is shown, when close button is clicked. Default is `true`. 
* Support for adding min and max values to AdjustmentOptions.

### Fixed
* Sticker and Text-Design previews occasionally failing to load.
* The background of the editor flickers transparently at startup.
* [VideoEditorSDK] A rare NullPointerException when changing the tool.
* [VideoEditorSDK] A rare RuntimeException whenever the timeline was shown due to a recycled Bitmap being drawn.
* [VideoEditorSDK] Bad video quality if auto bitrate is used.

### Changed
* Removed the `OrientationSensor` class to comply with privacy policy guidelines of certain third-party app stores.
* Deprecated `AutoRotateLayout`, `AutoRotateImageSource`, and `AutoRotateTextView` views.

## v10.6.0

### Added
* [VideoEditorSDK] Support for custom audio selection fragment.
* [VideoEditorSDK] Added soundstripe audio selection fragment, with proxy or custom data fetch implementation.

## v10.5.0

### Added
* `CanvasSettings.moveSpritesWithoutSelection` to control moving sprites around the canvas without selecting them first.
* `CanvasSettings.autoSelectSprites` to control automatic selection of sprites when added to the canvas.
* Support for custom dynamic smart stickers via `MetadataImageStickerItem`.

## v10.4.4

### Fixed
* [VideoEditorSDK] Audio overlay start time is not reset to zero, after switching audio overlay.
* [VideoEditorSDK] Fixed a potential IOException for some video or audio assets.
* The same layer instance could be added multiple times to the layer list, with leading to reduced performance.
* IMGLYFileReader sometimes added Brush, Frame, and Overlay layers multiple times.
* Loading serialization at runtime could cause a NullPointerException crash.
* Incorrect brush face aspect ratio (not round) after switching source.

__Note: If serializations contain the same layer (Brush, Frame, or Overlay) multiple times, this issue can be fixed by loading and saving the serialization in the editor.__

## v10.4.3

### Fixed
* When pressing discard, the user is stuck in an invisible audio tool.
* Slow devices can cause audio to stutter.
* If the same video appears twice in a list, only the first video can be trimmed.

## v10.4.2

### Fixed
* `ForceCropMode.SHOW_TOOL_WHEN_CROP_UNMATCHED` was not working resulting in silent crop.
* VectorDrawables are sometimes invisible.
* Video thumbnails are rotated in the composition and trim tool when the video is recorded in a rotated orientation.
* Individual video clip play/pause state not getting updated in Video Composition tool.
* Tool not showing sometimes on startup in case of force crop/trim.
* `IllegalStateException` in `VideoEditorActivityResultContract` and `PhotoEditorActivityResultContract` when parsing `null` intent.
* `SecurityException` when reading a `Uri` without read permission.
* `CalledFromWrongThreadException` when unable to prepare output `Uri` in `CameraPreviewActivity`.
* History state (undo/redo operations) was not restored after process death.
* Certain emojis/characters were breaking on changing the text width/height.

## v10.4.1

### Fixed
* [VideoEditorSDK] Stutter/noise/audio cracking sound in certain videos and audio overlays.

## v10.4.0

### Added
* `singleToolUse` mode that skips main menu if only one tool is used.

### Fixed
* Smart stickers not getting rendered during headless/background export.
* [VideoEditorSDK] Setting multiple video clips only set the first clip.
* [VideoEditorSDK] Incorrect exit animation for `AudioOverlayOptionsToolPanel`. 

## v10.3.3

### Fixed
* Synchronous serialization loading sometimes crash due to a concurrency bug.

## v10.3.2

### Fixed
* `CustomStickersFragment::onHiddenChanged()` called successively with `true` and `false` when re-selecting a `CustomStickerCategoryItem`.
* Unable to open `CameraPreviewActivity` with `CameraPreviewBuilder` using the default permissions on Android Tiramisu.
  * Restricted `WRITE_EXTERNAL_STORAGE` and `READ_EXTERNAL_STORAGE` permissions to `maxSdkVersion` 32.
  * Added `READ_MEDIA_IMAGES` permission to the camera module.
* `FileNotFoundException` when trying to make a local copy of `Uri`s.
* Changing serialization at runtime creates a memory leak.  
* Synchronous serialization loading crash if not all backend modules are included. 

## v10.3.1

### Fixed
* Layers were created using default layer settings during deserialization even after using `StateHandler.replaceStateClass()`.
* Generated sources not getting resolved with Kotlin 1.7.0+.
  * This is partially a bug in Kotlin and is expected to be fixed in Kotlin 1.7.20. Refer [KT-53688](https://youtrack.jetbrains.com/issue/KT-53688) for more details.
* [VideoEditorSDK] Setting `allowFastTrim` to false didn't result in re-encoding if there were no modifications made to the video.

### Changed
* Deprecated `ImgLyIntent::startActivityForBroadcast()` methods.
* Refactored all public methods accepting an `ArrayList` to accept the super class `List`.

### Added
* Added new developer API to `IMGLYFileReader` for reading image together with a serialization. 
  * Use ex. `IMGLYFileReader(stateHandler).readJson(input: InputStream, source: Uri)` to load the image in sync with the serialization.

## v10.3.0

### Fixed
* Overlapping dialogs when video source is broken.
* Source generating task not getting registered for dynamic feature modules.
* `ClassCastException` when using `UiConfigText.setFontList()` with a list that is not a `DataSourceIdItemList` or its subclass.
* `NullPointerException` when checking for Uri permission on Vivo devices.
* Possible Fatal Error 6 native crash on Android 8/9 devices, when using the composition or trim feature.

### Added
* [VideoEditorSDK] Added duration action for text and stickers. 

## v10.2.1

### Hotfix
* `NoClassDefFoundError: Failed resolution of: Lly/img/android/pesdk/ui/audio_composition/R$id` crash.

## v10.2.0

### Changed
* `PhotoEditorSettingsList` and `VideoEditorSettingsList` now includes basic assets by default (if present in runtime classpath).
* Bumped `compileSdkVersion` and `targetSdkVersion` to 33 and `buildToolsVersion` to 33.0.0.

### Added
* Refactored IMG.LY Gradle plugin to support new Gradle versions.
* Screen orientation support via `UIConfigScreenOrientation`
* DayNight theme variant (`Theme.Imgly.DayNight`)
* Added notification permission and new image picker support for devices running on Android 13 and newer OS versions via `AndroidConfiguration` class. 

### Fixed
* `CompostionPart` trim values are not preserved after closing the video editor.
* Changing `LoadSettings.source` did not change the video source at runtime.
* `android.permission.CAMERA` was being added even without including the `ui:camera` module.
* IMG.LY Gradle plugin did not support some build flags:
  * `android.defaults.buildfeatures.buildconfig=false`
  * `android.defaults.buildfeatures.resvalues=false`
  * `android.nonTransitiveRClass=true`
* Color defaulted to transparent on dismissing panel without selecting any color.
* GPU Memory leak on configuration changes.
* `java.io.IOException: Failed to instantiate extractor` crash.
* Giphy stickers inconsistent grid ratio.
* ClassCastException when `onOperatorReleased()` was called before `render()` in `RoxVideoCompositionOperation`.

## v10.1.1

### Added

* Plugin marker artifact to support `plugins` DSL block.

### Fixed

* Both `backend-video-core` & `backend-photo-core` were erroneously getting included in PE/VE SDK only projects.

## v10.1.0

### Added

* Support for background removal on photos and stickers.
* Support for custom sticker selection fragment.
* Giphy sticker provider integration with Sticker Tool.

### Fixed

* `InflateException` in `VideoCompositionToolPanel` when `ui:adjustment` module not included.
* `VideoTrimToolPanel` not showing in trial mode with default configuration.
* `UiConfigComposition.allowAddVideoClips` was being ignored.
*  Confirm Popup chopped off CTAs when description is small.
*  `NetworkOnMainThreadException` when using remote sticker sources.

## v10.0.1

### Fixed

* Crash caused by incorrect API usage due to incorrect Android documentation.

## ~~v10.0.0~~ (Broken release, don't use it!)

### Changed

* Bumped `minSdkVersion` to 21.
* Bumped `compileSdkVersion` to 31 and `buildToolsVersion` to 31.0.0.  
* Our activities now extend from `AppCompatActivity` instead of `Activity`.
* `PESDKMobileUIDefaultTheme` is now `Theme.Imgly`.
* `Imgly.Theme` is now `Theme.Imgly`.  
* `RoxOperation::onReleaseOperator()` renamed to `RoxOperation::onOperatorReleased()`.
* `startActivityForBroadcast()` is deprecated. Use `DocumentRenderWorker` instead.
* `ly.img.android.pesdk.backend.model.state.VideoCompositionSettings.VideoPart` deprecated in favor of `ly.img.android.pesdk.backend.model.VideoPart`.
* `VideoCompositionSettings.Event.VIDEO_SELECTED` deprecated in favor of `VideoState.Event.VIDEO_SELECTED`.
* `EditorBuilder::startActivityForResult()` is now in sync with `PhotoEditorBuilder::startActivityForResult()` and `VideoEditorBuilder::startActivityForResult()` and doesn't ask for any permissions.
* Certain parameters in the following functions were incorrectly declared as nullable which could lead to a `RuntimeException`. They are now non-null.
  * `ImglyIntent::startActivityForBroadcast()`.
  * `PhotoEditorBuilder::startActivityForBroadcast()`.
  * `VideoEditorBuilder::startActivityForBroadcast()` and `VideoEditorBuilder::startActivityForResult()`.
  * `EditorBuilder` and its subclasses' constructors.

### Added

* Light(`Theme.Imgly.Light`) theme variant.
* Batch background renderer.
* New constructor in `PhotoEditorBuilder` and `VideoEditorBuilder` to open a custom `Activity`.

### Fixed

* Audio surge.
* A couple of Video crashes.
* Unable to replace sticker sometimes.
* Serialisation compatibility issues with iOS/Web.
* Time units used in "Video too short" dialog are now translatable (`imgly_hour_unit`, `imgly_minute_unit`, etc.).
* Blinking effect when image/video is loaded in the editor.
  * 🚨 `android:windowBackground` now refers to `?attr/imgly_background_color`.
* Close Editor alert message adjusted based on PE/VE SDK (`pesdk_editor_text_closeImageEditorAlert`, `pesdk_editor_text_closeVideoEditorAlert`).

## v9.2.0

### Added

* Support for a custom watermark image.

### Fixed
* `ConfigLoader` (React Native, Flutter, Cordova/Ionic).
  * Crash if the system language is Turkish, because the `i` is converted to a `ı`.
  * `personalVideoClips` was being ignored in the `composition` object.
* OutOfMemoryError in `AudioPlayWindowSliderView` when all video composition parts are removed.
* NullPointerException on denying `WRITE_EXTERNAL_STORAGE` permission when exporting media on Android API versions < 29.
* NullPointerException when trimming a video.
* Memory leak in `DataSourceListAdapter`.
* Memory leak after removing Sticker, Text design or Text.
* Menu items for unavailable modules were being added to `UiConfigMainMenu` by default.
* NullPointerException caused by invalid IFD0 offset during EXIF parsing.
* Audio tool panel doesn't work if `Trim` feature is not included in the license.
* Exporting video in background doesn't respect specified duration limits.
* Image overflow on applying frame if UI lifecycle state changes.
* Missing filter categories thumbnail for existing categories.
* Export hangs if the audio track is unsupported or broken.
    * Those tracks that can't be decoded, such as ALAC (Apple Lossless Audio Codec) encoded tracks, are ignored.

## v9.1.1

### Fixed

* Trim cannot be serialized if you select only start or end time.
* Some videos play audio with artifacts, because of broken time header.
* Discarding text change removes text sprite.
* NullPointerException when loading a Uri with null authority.
* Video and/or video thumbnails may turn black after resuming the application.
* Video parts could hang if only two videos be composed.
* In cases where more than one video part is composed, video parts could be black after app recovery.

## v9.1.0

### Fixed

* Unable to open photo/video picker when targeting Android API 30+.
* Panel title wasn't getting updated on locale change.
* Crash when all required permissions were not granted on opening Camera.
* IMG.LY plugin incompatibility issue with Kotlin Gradle plugin 1.5.30+.
* Crop aspect ratio icons were not getting updated correctly on scroll.
* [VideoEditorSDK] Background export was not working.

### Added
* AudioOverlay to serialisation.
* Support for loading static frames from remote URLs.
* Support for the new ActivityResult API via [PhotoEditorActivityResultContract], [VideoEditorActivityResultContract], and [CameraPreviewActivityResultContract].

### Changed

* Removed unused Renderscript flags from Android defaultConfig block.
* All Kotlin classes extending from `ImglySettings` are now open.
* Added missing `@Throws` annotation for some methods in `ly.img.android.serializer` package.
* Removed unused `android:label` and `android:supportsRtl` attributes from `<application>`.
* 🚨 img.ly maven repository is no longer automatically added to your project by the plugin.
    * Refer to our new Getting Started guide for instructions on how to add it.
  

## v9.0.0

### Changed

* Migrated to AndroidX

__________________________________________________________________
🚨 __We do not plan to release new feature updates to v8. Only the most critical bug fixes will be back-ported to v8.__
__________________________________________________________________


## v8.3.4

### Fixed

* In some cases, a non-local content URI can cause an `IllegalArgumentException("column '_data' does not exist")` resulting in a crash.
* Export of images imported using MediaStore Uri was failing on Android 10 and above.
    * To preserve GPS-IFD EXIF tags in the exported image, ACCESS_MEDIA_LOCATION permission is required on Android 10 and above.
* `EditorShowState.Event.PREVIEW_IS_READY` was getting dispatched along with `EditorShowState.Event.IS_READY` and vice versa.
* `VideoComposition.Event.STATE_REVERTED` was getting dispatched along with `AudioOverlaySettings.Event.STATE_REVERTED` and vice versa.
* [VideoEditorSDK] "Add more clips" is now a translatable string. It is now called "vesdk_trim_add_clip_button".


## v8.3.3

### Fixed
* TextDesign has added flipped horizontally if the image is horizontally flipped. 
* If the image is flipped after adding Text, Text is flipped horizontally (while TextDesign is not). 
* Poor video quality, when using `TAKE_SOURCE_BIT_RATE`.
* `UnsupportedClassVersionError` when applying the `IMGLYPlugin` in projects compiling using JDK 1.8.
* The history sometimes contains duplicate entries.
* The history sometimes changes the UI state.
* Sometimes the history is destroyed or in the wrong order.
* Personal sticker rotation is incorrect after undo.
* Incorrect orientation of HEIF images.
* After resuming the app without a license, the editor preview is black.
* The editor preview becomes black after resuming while in the overlay tool.
* Editor crashes after closing with a `StateUnbindedException`. 
* Exception is named `StateUnbindedException` instead of `StateUnboundedException`.
* `LOADING_START` was getting dispatched along with `LOADING_FINISH` and vice versa.

### Added
* `VideoEditorSaveSettings.bitsPerPixel` as alternative to `VideoEditorSaveSettings.bitRate`.


## v8.3.2

### Fixed
* Typo in IMGLYPlugin config. Renamed `licencePath` to `licensePath`.  
* Typo in property `resultIsDifferentThanSource` of `EditorSDKResult`.
* The transparency pattern is drawn to the full size of the canvas, rather than just the image area.
* Thumbnail for custom stickers group wasn't getting updated on adding a new custom sticker.
* Color is applied to the wrong sticker layer when another sticker layer is selected from the color tool panel.
* Color is applied to the wrong text layer when another text layer is selected from the color tool panel.

## v8.3.1

### Fixed
* ConfigLoader (React Native, Cordova/Ionic).
  * Auto generated video thumbnails are missing.
  * `clipTrim` settings are ignored.
* Some colors of the light color theme were not correct.
* Filter live view thumbnails loose context when getting app back from background or not using it for a while.
* Dynamic Time sticker doesn't represent current value all the time.
* Single Brush points not rendered.
* The value for the frame width is displayed inaccurately.
* The `OutputMode.EXPORT_IF_NECESSARY` output mode does not work.
* Entering gallery from `CameraPreviewActivity` did not work on Android API 30 or higher.
* [VideoEditorSDK] Video output resolution is sometimes too low. (Se also `VideoEditorSaveSettings.allowOrientationMatrixMetadata`)
* 🚨 The minimum size of a text sticker was too big compared to iOS. (To restore the old behavior, set `TextLayerSettings.MIN_STICKER_SCALING = 0.05`.)

### Added
* [VideoEditorSDK] Enable VideoEditorSaveSettings.allowOrientationMatrixMetadata, can increase the output resolution of portrait video on low-end phones. (Note that some video players may choose to ignore the matrix metadata.)


## v8.3.0

### Added
* [VideoEditorSDK] Added video clip library for adding predefined video clips to video compositions.
* [VideoEditorSDK] Added audio tool for adding overlay audio tracks to the edited video.

### Fixed
* Poor utilization of available resources resulting in bad background thread performance.


## v8.2.0

### Added
* [VideoEditorSDK] Added composition tool for creating video compositions from multiple video clips. (Licence required)
* [VideoEditorSDK] Added play/pause overlay button to the `MenuToolPanel`.
* [VideoEditorSDK] Added sound on/off overlay button to the `MenuToolPanel` which mutes and unmutes the audio in the preview player and removes the audio track for exported videos if audio was muted in the preview.

### Fixed
* Slower export speed if background module is included.
* High workload on main thread, while editor starts. 


## v8.1.3

### Fixed
* Constructors of some classes are handled incorrectly which results in `IllegalArgumentException`.
* Missing keep annotation in ProGuard rules.
* The Alpha channel of exported transparent images has the wrong colors.


## v8.1.2

### Fixed
* "Configuration with name 'compile' not found" issue while compiling with newer Gradle version.
* Text alignment is not serialized correctly.
* Exporting in some specific resolutions results in an endless export spinner.
* The sticker `imgly_sticker_emoticons_grin` got a wrong option mode.


## v8.1.1

### Fixed
* `NetworkOnMainThreadException` while reading or writing serialization. (Please make sure to read and write the serialization only from a WorkerThread.)
* Out-of-index OpenGL warnings thrown by some GPU drivers.
* `pesdk_editor_button_somethingWentWrongCloseEditor` string value was never used by the `imgly_popup_error_dialog.xml`.


## v8.1.0

### Improved
* Reduced the methods count of the serializer module by about 12,5k methods.

### Fixed
* IllegalArgumentException: "Cannot round NaN value" when adding `TextGlLayer` before staring the editor.
* Sticker tint color is not written to serialization.
* Color lists does not scroll to the position of the selected color.

## Changed
* The displayed title of text input changed to "Add Text".
* Default blend mode of the vintage overlay has changed from `BlendMode.MULTIPLY` to `BlendMode.OVERLAY`.

## Added
* `pesdk_text_title_input` and `pesdk_textDesign_title_input` to string values to make the text input titles configurable.


## v8.0.9

### Fixed
* The app freezes when the export finishes while the editor is in the background.
* The `hasChanges` flag is true if the user changes something but revert it. 
  * This can result in exports even if nothing has changed.
* The Editor preview is broken after export.
* The @throws annotation of some methods are broken since v8.0.0 because of internal kotlin changes.
* Video export progress is wrong if export is trimmed.
* The `TransformTool` opens even if there is a `CropAspectAsset` which fits the aspect ratio and the `ForceCrop.SHOW_TOOL_WHEN_CROP_UNMATCHED` is selected.
* Videos with odd width or height of the crop results in a crash and an endless loop while exporting.

### Added
* Overridable `onExportDone(result: EditorSDKResult) : Boolean` in EditorActivity, allows to exports multiple times.
* Overridable `onExportStart(stateHandler: StateHandler)` in EditorActivity, allows to change settings before starting to export.


## v8.0.8

### Fixed
* ConfigLoader (React Native, Cordova/Ionic).
  * 🚨 Wrapper, always export even without changes.
* EditorSDKResult.resultIsDifferentThenSource is always true.
* 🚨 The result URI is null if export would be skipped, but should be the source URI instead.
* Crash when drawing with a large brush face on a huge image.
* Brush is sometimes not drawn completely.
* Colored brush has bad quality.
* Maximum Video resolution is calculated wrong, results in an endless loop while export. 


## v8.0.7

### Fixed
* Write serialization as ByteArray is broken.
* ConfigLoader (React Native, Cordova/Ionic).
  * Aspect Ratio labels do not display.
* LayerSettings are not replaceable by `StateHandler.replaceStateClass`.
* [VideoEditorSDK] Video disappears when resume from background.
* [PhotoEditorSDK] Image disappears when resume from background.
* Some classes and methods are not open as in version 7.
* `OutOfMemoryException` of the brush tool.
* v8.0.6 is not compilable with ReactNative or Cordova wrapper.


## v8.0.6

### Fixed
* 🚨 `WRITE_EXTERNAL_STORAGE` was notated with a maximum API level of 28.
* Font icons of `TextTool` not rendered correctly at all. v8.0.3 and v8.0.5 does not fixing this issue.
* Artifacts on some devices while loading image or video.
* Serialization rewritten for better performance, smaller code, and removing of third-party library.


## v8.0.5

### Fixed
* Camera do not capture an image if `doOpenEditorAfterCapture` is false.
* Font icons of `TextTool` not rendered correctly on API 23 and below.
* Some classes and methods are not open as in version 7.
* Editor crashes when using `SaveSettings` instead of `VideoEditorSaveSettings` or `PhotoEditorSaveSettings`.


## v8.0.4

### Fixed
* Some assets are removed by history.
* Sound is broken while preview and export.
* Editor crashes without configuring `VideoEditorSaveSettings` or `PhotoEditorSaveSettings`.
* Filename of exported videos are prefixed with "img_" by default.
* `CameraPreviewActivity` is not open to be extensionable.
* Adding multiple stickers at the same time. 
* Fast trim video export crashes on some devices.


## v8.0.3

### Fixed
* HEIC images does not orientated correctly.
* Brush tool colorize the whole picture in some cases.
* The color of the text font icons are always overwritten with white. It is now using `imgly_icon_color` as color resource.
* Trim settings and serialization are ignored at startup.
* Overriding annotation processor arguments.


## v8.0.2

### Fixed
* `IllegalArgumentException` while exporting a video on some devices.


## ~~v8.0.1~~ (Deprecated version, don't use it!)

### Fixed
* `KotlinNullPointerException` if using a relative export uri.
* Bitrate value are not clamped within the valid bitrate range.
* Crash on Android 11 while exporting JPEG format on some devices.
* Brush size is sometimes too big.


## v7.6.6

### Fixed
* Brush size is sometimes too big.
* Android 11 ScopedStorage `requestLegacyExternalStorage="true"` attribute together with target 29 is not working.

__________________________________________________________________

🚨 
__This is the latest update of v7, we will not support v7 anymore please update to v8 as soon as possible__

V7 will only run with `targetSdkVersion 29` and `requestLegacyExternalStorage="true"`.

We recommend updating to v8 follow our migration guides [Migration from v7](https://docs.photoeditorsdk.com/guides/android/v8/introduction/migration). 
v8 it will run with `targetSdkVersion 29` and above with or without `requestLegacyExternalStorage`. 

As of October 15th. In 2020, Google will continue to allow `targetSdkVersion 29` and has not yet announced an end of support date. 

Please inform yourself regularly on the following website.:<br>
[Google Play’s target API level requirements](https://support.google.com/googleplay/android-developer/answer/113469#targetsdk) <br>
[Meet Google Play's target API level requirement](https://developer.android.com/distribute/best-practices/develop/target-sdk)

__________________________________________________________________


## v8.0.0

### Added
* Android 10 and Android 11 scoped storage support.
* Kotlin 1.4.10 support.
* Gradle 6 compatibility.


## v7.6.5

### Fixed
* Font preview not centered in UI, please update your `imgly_list_item_font_big.xml` and `imgly_list_item_font.xml` layouts.
* Missing frame aspect tolerance value (`FRAME_THRESHOLD`) in `FrameAsset`.

### Changed
* Replaced several fonts with new fonts and added additional new fonts.


## v7.6.4

### Fixed
* `ArrayIndexOutOfBoundsException` in `TextInBoundsDrawer`.


## v7.6.3

### Fixed
* 🚨 [VideoEditorSDK] The 'backend:headless' module produced an "attempting to declare permission" error while installing. Please look how to update your AndroidManifest.xml at https://docs.videoeditorsdk.com/guides/android/v7_6/concepts/background_export
* Trim settings are not serialized correctly.
* The checkerboard texture of transparent image areas being displayed incorrectly.


## v7.6.2

### Fixed
* The rotation of exported videos being incorrect in some cases.


## v7.6.1

### Fixed
* The brush color default values ​​are overwriting the custom brush colors.
* WeatherSticker not updated in UI.
* WeatherSticker can freeze the UI.
~~* The rotation of exported videos being incorrect in some cases.~~ (See v7.6.2)


## v7.6.0

### Added
* `ExternalVideoCaptureBuilder` which opens the system's default video recorder app.

### Fixed
* Wrong icons for opacity and replace in the frame options.
* First chunk render sometimes in bad quality.
* Crop reset does not reset the crop area correctly.
* SmartStickers can crash in release version. Because of a missing Proguard / R8 rule. 


## v7.5.0

### Added
* Smart Sticker module `:backend-sticker-smart` containing `SmartStickerPack` with 6 predefined SmartSticker.
* `WeatherProvider` base class for `SmartWeatherCloudSticker` and `SmartWeatherThermostatSticker` 
* `CanvasDecoder.Drawable` can be used to implement your own SmartSticker.
* `StickerPackAnimated` which includes gif stickers for VideoEditor SDK. If used in PESDK the first frame is displayed.

### Changed
* The sticker category of `StickerPackEmoticons` contains Smart Stickers if the `:backend-sticker-smart` is included.

### Fixed
* `Bring to front` button is not greyed out if the brush layer is already at the front.


## v7.4.0

### Fixed
* Source type is `UNKNOWN` instead of `BROKEN` if `IS_READY` event is called.
* Gallery of the Camera module did not explicitly select only photos.
* Personal sticker upload allows to select a video which results in a broken sticker.
* Camera has wrong size after resume the activity.
* Emoticons are not filtered by text and text design.
* `OPTION_TO_FRONT` is disabled and the `bring to front` button is not greyed out if the selected layer is already at the front. (Make sure the item is a `ToggleOption` item and your drawable is a `SelectionList` with enabled state.)
* TextDesgin and Text stickers can be reduced too much in size.

### Added
* Popup dialog box that appears when an incorrect data format is passed to the editor.
* `ToggleOption` item can be used to customize an Option that can be disabled and enabled.
* `MIN_STICKER_SCALING` and `MAX_STICKER_SCALING` to `TextLayerSettings`, `TextDesignLayerSettings` and `ImageStickerLayerSettings`.


## v7.3.1

### Improved
* Performance of the user interface.
* Resolved some compile-time warnings.

### Fixed
* HistoryOption Button is flickering while editing the image.
* Icons are sometimes not loading.
* `ConcurrentModificationException` in `TextInBoundsDrawer`.
* `NullPointerException` in `EditorShowState`.
* Some OnEvent annotations are not called initially, which resulted in the wrong UI state.
* [VideoEditorSDK] Export hangs if the video is stopped in preview.
* [VideoEditorSDK] Seeking preview sometimes hangs.

### Known issue
* `StateUnbindedException` is thrown under rare and unknown conditions but `StateUnbindedException` was the wrong exception. Because we can't reproduce the issue, we have corrected the Exception, it's now a RuntimeException with a more detailed stack trace. Now we need to wait on your customer feedback to fix this issue. Thank you for your understanding.


## v7.3.0

## Added
* [VideoEditorSDK] Animated GIF Support, `include 'backend:sticker-animated'` to enable it.

### Fixed
* Broken limits for moving the stickers outside the image border.

### Know issues
* [VideoEditorSDK] Background rendering with animated GIF has currently an undefined behaviour, don't use `startActivityForBroadcast()` together with the `'backend:sticker-animated'` module. 


## v7.2.6

### Fixed
* Possible dead lock of the main thread after restart Video editing.
* Sticker, Text and TextDesign is sometimes missing at the first frames of a video.
* Sticker, Text and TextDesign is sometimes black after recover app from background.
* Text preview is shifted and stretched.


## v7.2.5
 
### Fixed
* `NullPointerException` if a captured image was exported with `SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT`.
* External FontAssets created with URI are sometimes not loaded correctly.
* Exported Video aspect is not correct after cropping on some devices.


## v7.2.4
 
### Fixed
* In exceptional cases, the preview image is not displayed.


## v7.2.3

### Fixed
* `SORTED_ROTATION_SNAP_POINTS_45` and `SORTED_ROTATION_SNAP_POINTS` of `TextDesignGlLayer` are not customizable.
* `IllegalArgumentException` when exporting a video and a remote uri was used.


## v7.2.2

### Added
* Colors: `imgly_editor_position_snap_indicator_color`, `imgly_editor_rotation_snap_indicator_color` and `imgly_editor_bounding_snap_indicator_color` to the `imgly_color.xml` to make the colors of the snapping indicator customizable.
* `NotAnImglyResultException` if intent is not a valid `EditorSDKResult`.

### Fixed
* None frame asset just included in the `assets:frame-basic` package.
* `imgly_sprite_handle_line_color` and `imgly_sprite_handle_thumb_color` got ignored when used to customize the sprite handle's colors.

### Changed
* Getting started guides and activity examples no longer display legacy code. 


## [Edited]v7.2.1

### Fixed
* Android preview freeze after pause and resume the app.
* EditorSDKResult.notifyGallery() parameter are ignored, notify always for source and result.
* "Unsupported save policy" RuntimeException.
* New app permissions are needed for headless export, without using headless export. 
    * 🚨 We fixed that issue by making Headless rendering optional, is not longer available by default (opt-in)!
    * 🚨 Add the `include 'backend:headless'` to the `modules` in the `imglyConfig` of your build.gradle file to enable it.

### Added
* `VideoEditorSaveSettings.setAllowFastTrim(true|false)` option for speed up trimming only operation without transcoding.
[Edited] * `EditorSDKResult` to wrap the intent for better API access.


## ~~v7.2.0~~ (Deprecated version, don't use it!)

### Fixed
* Exported image has shifted lines sometimes.
* Broken behavior if changing the crop size of an rotated image, manually.

### Added
* Use `startActivityForBroadcast(Activity/Fragment context, final String broadcastReceiverName)` to enable background exporting (see [Background Exporting](https://docs.videoeditorsdk.com/guides/android/v7_2/concepts/background_export).
* Set `RenderService.createNotification = {(notification) -> }` to change the Notification Style.
* Set `RenderService.updateNotification = {(notification, progress) -> }` to change the Notification Update Style.

### Changed
* Export starts a foreground service to continue exporting while app is in background.


## v7.1.13

### Changed

* Developer behavior 
  * `IllegalArgumentException` if there is no `CropAspectAsset` defined.

* ConfigLoader (React Native)
  * 🚨 Default behavior of custom LUT filter.
  
### Fixed

* General
  * `ConcurrentModificationException` in some cases while loading a serialization file.

* Rendering
  * Bad export quality of text.
  * Bad export quality of frame on a large image.
  * Crashes and freezes when rendering headless.
  * Wrong configuration of some frames (no noteworthy visual change).
  * Wrong image sizes for JPEG export in some cases.

* Video
  * 🚨 TrimToolPanel UI differences to iOS platform.
  * 🚨 TrimToolPanel just fade in, instead of translate and fade.

* ConfigLoader (React Native)
  * 🚨 Default serialization export type.
  * Wrong behavior if no crop aspect is defined and force crop is enabled.
  * Relative import paths are converted incorrectly, which leads to an export crash.


## v7.1.12

### Added
* Support for URIs with `asset` schema necessary for bundling static assets with Expo.

### Fixed
* Frame not displayed correctly while exporting.
* Options not serialized correctly in some cases.

## v7.1.11

### Fixed
* Focus blur creates artifacts when exporting large images.
* Frame export is different to frame preview.
* Serialization wrote wrong crop identifier and do not set the dimensions.
* Proguard rules of config loader necessary for React Native / Expo.

## v7.1.10

### Fixed
* Workaround: Broken URI-parsing within React Native in the release mode.
* The video export freezes if audio track not exist or if it is broken.

## v7.1.9

### Fixed
* `OutOfMemoryException` after several image exports caused by the JPEG encoder.
* `ConcurrentModificationException` while loading a serialization.
* Deserialize an image with a fixed crop aspect ratio yields wrong result.
* Some feature combinations can cause a `IndexOutOfBoundsException`.
* Set an `Imgly.Theme` got ignored.

### Added
* `IllegalArgumentException` if selecting `CropAspectAsset.FREE_CROP` for static frames.

## v7.1.8

### Fixed
* Configuration of some frames are wrong.
* Missing exif information after export.
* `GlSurfaceView` is sometimes not rendering correctly.

## v7.1.7

### Fixed
* `ConfigLoader` does ignore items property of `ExistingCategory`.
* Crashes or black images parts on some devices because of a OpenGl driver bug.
* Loading frame serialization can cause a crash.
* Configurations got ignored when using `SaveSettings`.
* Filter and Focus maybe broken.
* Exported image sometimes displaying green.
* Crash that sometimes occurred when loading serialization.

## v7.1.6

### Fixed
* Blur rendering quality sometimes bad.
* Text element still exists after deleting the content.
* Text design element still exist after deleting the content.
* Uploading a custom sticker causes a crash.
* Config Loader ignores personal sticker property if category property was defined.

### Improved
* Image export preview.

## v7.1.5

### Added
* Child friendly version of `StickerPackEmoticons`.

### Fixed
* Wrong item type in default `UIConfigSticker.quickOptionList`.
* A circular crop mask is in the defaults.
* Choose of a wrong force crop for landscape images.
* Video timer calculation shows wrong time.

### Removed
* Circular crop mask.

## v7.1.4

### Added
* Universal config loader for frameworks like react native.

### Changed 
* 🚨 Some of the public API method has changed when accessed from kotlin. This is because we have convert some of our Code from JAVA to Kotlin internally.  

### Fixed
* JPEG Quality is bad, because of a wrong discrete cosine transformation.

## v7.0.10

### Fixed
* QuickOptions are sometimes not shown.
* Rotation snapping guides are incorrect when the image is rotated. 
* TransformSettings.setForceCrop() choose the wrong ratio after loading a serialization.
* Issue when using ConfigMap.addOrReplace(). 
* \[VESDK\] Video with special audio specs, does not export.
* \[VESDK\] Video with special audio specs, sound is fast forwarding in preview.

### Added
* We cut out left and right screen area of HorizontalListView and Seek Slider from system gestures in Android 10, to preserve a intuitive handling.
* `AssetConfig.addAsset(Boolean overrideExisting, @NonNull AbstractAsset... configs)` allow you to override existing assets.
* `ConfigMap.remove(String id)`.

## v7.0.9

### Fixed
* TransformSettings.setForceCrop() throwing `ArithmeticException. 

## v7.0.8

### Fixed
* Video export says "Exporting Image".
* ~~TransformSettings.setForceCrop() throwing `ArithmeticException.~~ 
* Video encoding takes some while at the end of stream.
* Deadlock while editing.

## v7.0.7

### Added
* Option for the user to choose a sticker from your gallery, add `new PersonalStickerAddItem()` to your StickerList config to enable it.

### Fixed
* Serialization is written with wrong sizes if the image rotation is adjusted.

## v6.6.4

### Fixed
* Serialization is written with wrong sizes if the image rotation is adjusted.

## v7.0.6

### Fixed
* Some focus operations are not correctly serialized.
* Frame disappears after accepting in frame tool panel.
* Color pipette picks are not reverted after cancel.
* NPE if brush is not part of the license feature list.
* Tool list can't be changed.
* Migration task crash with `No signature of method: java.lang.String.findIndexOf()` 

## v7.0.5

### Improved
* Video decoding speed.
* JPEG encoding speed.

### Fixed
* Layer randomly not serialized (sometimes cause crashes).
* Sticker position is wrong after loading a serialisation.
* NPE with some limited licenses combinations. 
* StickerCategoryItem equals method ignores id. 
* The default focus highlight rect appears sometimes.
* Text disappears unexpectedly after cancel font or color changes.
* TextDesign sometimes shifted after loading form serialization.
* Sticker not movable after click event.
* After leaving the transform tool the image is not fit to screen.
* Random NPE in Focus Tool.

## v6.6.3

### Fixed
* StickerCategoryItem equals method ignores id.
* The default focus highlight rect appears sometimes.
* Text disappears unexpectedly after cancel font or color changes.
* TextDesign sometimes shifted after loading form serialization.
* Sticker not movable after click event.
* Random crash after fast tool change. 
* After leaving the transform tool the image is not fit to screen.
* Random NPE in Focus Tool.

## v7.0.4

### Fixed
* SaveSettings are ignored.
* Sticker color can not applied under specific conditions.
* Image zoom out after option changes.
* Unwanted painting on the image after zoom with activated brush tool.
* Native Android crash on some devices after change the brush size.
* TextDesign layout do not change in the UI after switching to another the TextDesign sticker.

### Changed
* Updated kotlin version to `1.3.50`.
* Internal use of build tools `29.0.2`

### Added
* Missing feature flag from v5 `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT` if false the bounding box is not auto fitting after font changes.

## v6.6.2

### Fixed
* The opacity adjustment of stickers cannot be undone.
* Sticker color can not applied under specific conditions.
* Image zoom out after option changes.
* Unwanted painting on the image after zoom with activated brush tool.
* Brush History is broken.
* Native Android crash on some devices after change the brush size.
* TextDesign layout do not change in the UI after switching to another the TextDesign sticker.

### Added
* Missing feature flag from v5 `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT` if false the bounding box is not auto fitting after font changes.


## v7.0.3

### Fixed
* The opacity adjustment of stickers cannot be undone.
* Error: `package android.support.annotation does not exist` when using AndroidX.

## v7.0.2

### Fixed
* Blur tile glitch.
* Export canceled in background.
* Filter preview sometimes broken.
* Missing thumbnail item in overlay.
* Battery drain on some devices.
* MathUtils.wrapTo360() is outside of range if value is negative.

## v7.0.1

### Fixed
* OOM if device report a too high maxTextureSize.
* crash on some older devices.
* Some gradle build issues.
* Brush is not drawn with the selected color.
* Wrong preview if image or video is rotated. 
* Kotlin extension `(Video|Photo)EditorSettingsList.configure<>{}` has return `SettingsList` instead of `(Video|Photo)EditorSettingsList`. 

## v7.0.0

###Added
* First release of VideoEditorSDK [videoeditorsdk.com](https://videoeditorsdk.com).
* You can use SourceType.detectTypeSafe() on WorkerThread to detect supported images and videos now.
* You can use SourceType.detectTypeFast() on AnyThread to detect images and videos by name.
* The gradle `pesdkConfig` is deprecated, but still compatible for PESDK, please use `imglyConfig` in the future.
* `VideoEditorBuilder` to create VESDK instances.

### 🚨 Changed
* Removed RenderScript support lib.
* Renderer now use OpenGl and a C++ JPEG compressor instead of RenderScript.
* `PESDKEvents.*` are deprecated now, and Events using Strings instead of enums now, *we will provide an gradle auto migration task soon.*
* `EditorSaveSettings` are now PhotoEditorSaveSettings and VideoEditorSaveSettings.
* `SettingsList` are deprecated now use `PhotoEditorSettingsList` and `PhotoEditorSaveSettings` instead.
* `EditorLoadSettings` are deprecated use `LoadSettings` instead.
* Removed `RelativeRectAccurate` class.

### Know limitations
* The Camera do not support video's use the device camera app instead to take photo's and video's

### Fixed
* AndroidX projects crashes.

## v6.6.1

### Fixed
* Deleting the last item of a `DataSourceArrayList` results in an IndexOutOfBoundsException.

## v6.6.0

### Fixed
* Doubled file extension suffix in the export when using names with only one letter.
* Text input field has wrong position, if activity is not in fullscreen mode.
* Broken style of text design color list.

### Added
* New themes `Imgly.Theme`, `Imgly.Theme.NoFullscreen`, `Imgly.Theme.TopActionBar` and `Imgly.Theme.TopActionBar.NoFullscreen` which you can set the fullscreen mode on/off and also the position of the actionbar to the top/bottom.

### 🚨 Changed Layouts (We need that due to an bugfix, please make sure you are using the new version from the default_res_files.)
** Remove wrong `rotation` attribute from `Imgly.PESDK.Editor.Panel.TextDesign.ColorList` style.
*  Changed View class of the text design color list view (`@+id/rv_text_colors`) from `RecyclerView` to `HorizontalListView` in the `imgly_panel_tool_text_design.xml` layout file.

## v6.5.2

### Added
* Drawable alias `imgly_transparent_identity_color_item` and `imgly_transparent_identity_image_bg` which refer to `imgly_transparent_identity` to make them separately customizable.
* 🚨 Drawable `imgly_transparent_identity_alpha_slider`. (Please download the default_res_files of the newest version to accept the new behavior.)
* New color resource `imgly_transform_background_color` for better customization.

### Changed
* Size of some elements of the color picker to achieve equality of the platforms.
* Size of the checker board texture `imgly_transparent_identity` to achieve equality of the platforms.
* Transform crop grid lines can set to 0 to be invisible.

### Fixed
* Typos in the adjustments `Shadows` and `Highlights`.
* Some color identifiers are overwritten incorrectly.

## v6.5.1

### Added
* `Sharpness` as a new adjustment value.

### Changed
* Kotlin version to `1.3.31`.

### Fixed
* TextDesign `Summer Feeling` has no lines (old Serialisation HTML5 and Android < 3.5.0 and iOS < 3.1.1 restore the old design).
* Bounding box of TextDesign `Deko` is too big (old Serialisation HTML5 and Android < 3.5.0 and iOS < 3.1.1 restore the old design).
* `Blacks` and `Whites` adjustment applied to alpha values.
* The serialization will not be serialized / deserialized properly if some modules are not included.
* Resource deprecation warning, keeps alive until restart gradle daemon.

## v5.1.12

### Fixed
* Wrong save policy when using camera.
* Wrong label of sticker color selection tool.

## v6.5.0

### Added
* Example light theme color schema to `imgly_colors.xml` and documentation. Due to a limitation of Android 4, it is currently not possible to change the colors theme at runtime, you need to add these colors at compile time.
* Color identifier to be able to customize in more detail.

### Fixed
* Background thread workload not balanced.
* Thumbnails not shown under certain conditions.
* Wrong tool stack handling.
* Wrong tool event calls on certain conditions. `LEAVE_TOOL` and `ENTER_TOOL` sometimes were in wrong order.
* Wrong PESDK plugin classpath in the `README.md`.

### Changed
* Rename internal RenderScript files to trigger recompiling
* Layout order of `imgly_list_item_filter.xml`, `imgly_list_item_filter_folder_subitem.xml` and `imgly_list_item_overlay.xml`.
* Names of some old color identifier.
* We've removed some legacy styles, make sure you stop using them or that you have a copy from an old version.
** `Imgly.PESDK.Editor.Popup.Brush.HorizontalMargin`
** `Imgly.PESDK.Camera.UI.Container`
** `Imgly.PESDK.Editor.Panel.default.Item.SelectBackground`
** `Imgly.PESDK.Editor.Panel.Overlay.OverlayItem.Value`
** `Imgly.PESDK.Editor.Panel.Transform.Crop`
** `Imgly.PESDK.Widget.GalleryButton`
** `Imgly.PESDK.Widget.GalleryButton.ClickOverlay`
** `Imgly.PESDK.Widget.GalleryButton.PreviewImage`

## v6.4.2

### Fixed
* Typo in the filter name 'High Carp'.

### Changed
* Name of filter 'Gysmo' to 'Colorful'.
* Name of filter 'Cotton Candy' to 'Candy'.
* Name of filter 'High Contrast' to 'Hicon'.
* Order of some filters to be equal to other platforms.

## v6.4.1

### Added
* Support for `com.android.tools.build:gradle` v3.4

## v6.4.0

### Dependency changes
* RenderscriptTargetApi version migrated from 19 to 28.

### Added
* 64bit compatibility.

### Fixed
* Some renderscript crashes.

## v6.3.2

### Fixed
* Incorrect proguard rules prevent proguard from optimizing optimally.

## ~~v6.3.1~~
* Because of an mistake, this release is equal to v6.3.0 and released without any fixes.

### ~~Fixed~~
~~* Wrong proguard rules.~~  

## v6.3.0

### Added
* Folders to the `FilterToolPanel` to group related filters together and to reduce clutter in the menu. To disable folders and get back the old behavior, simply use `getFilterPackWithoutFolders()` from `FilterPackBasic`.
* Duotone filters to default filter pack.

### Fixed
* Wrong save policy when using camera.

### Changed
* Displayed name of a couple of filters.

## v6.2.8

### Added
* `ColorPipetteItem` to color lists.
 
### Fixed
* Issue with multiple layouts.
* Events not fired after replacing a state or a settings class.

## v6.2.7

### Added
* Missing style attribute of `SeekSlider`.

### Fixed
* Wrong parceling of adjust options.

## v6.2.6

### Fixed
* Wrong parceling of tool list.

## v6.2.5

### Added
* Constructor to `PESDKFileReader` to pass `StateHandler` parameter.

## v6.2.4

### Changed
* Parceling from strict type to dynamic type.

### Added
* Setter method for brush color list.

### Fixed
* NPE, when loading broken vector drawables.
* Broken vector drawables.

## v6.2.3

### Fixed
* NPE if not all backend modules are included.
* NPE on load serialization from iOS with enabled autoEnhancement.

## v6.2.2

### Added
* Possibility to use a color pipette.

### Fixed
* Wrong parceling of brush color list.

## v6.2.1

### Fixed
* Wrong renderscript proguard rule for androidx.
* Some vector drawables are broken with android api level 21. 

## v6.2.0

### Fixed
* Sticker Tint mode is converted into ink mode after read a serialization.
* Fatal Exception: java.lang.NumberFormatException: Invalid int: "drawable/%"

### Added
* New `FilterAsset` called `DuotoneFilterAsset`.
* Missing renderscript proguard rule for androidx.

## v6.1.5

### Fixed
* Blacks and Whites Adjustment destroy alpha channel.

### Improved
* Camera preview position calculation for some devices.
* Invalidate renderscript caches.

### Added
* `AcceptTextButton` and `CancelTextButton` to allow text instead of icons in the `ActionBar`.

## v6.1.4

### Fixed
* Exporting image crashes app, when certain text conditions are met.

## v6.1.3

### Fixed
* Exif data is not copied.
* Text alignment configuration is not preserved.
* Style of the transform icon is not identical to other platforms.

## v6.1.2

### Fixed
* The input image is cached, which resulting in the image containing the correct edits, but on a different image than the input image after export.

## v6.1.1

### Fixed
* NullPointerException while entering the `Frame` tool.
* NumberFormatException while inflating layout.
* The editor can not load WebP images.
* Label of the `Original` item in the `Overlay` tool.

### Improved
* Speed to take a picture.

## v5.1.9

### Fixed
* The editor can not load WebP images.
* InflateException in combination with Android Bundles.  

## v6.1.0

### Fixed
* Sticker icons reload by clicking the same sticker category twice.
* The size of the image does not zoom out when you enter the frame tool.

### Added
* Possibility to add more than one `TextDesign` to canvas.
* Possibility to change the background size of an inverted `TextDesign`.
* Keeping the attributes of the most recently created `TextDesign`.
* Keeping the attributes of the mist recently created `Text`
* `FrameOptionToolPanel` which provides frame configuration.
* Possibility to adjust the opacity and width of a `Frame`.
* Possibility to adjust the opacity of a `Sticker`.
* Replace button to `StickerOptionToolPanel`.
* `ToggleAspectItem` which toggle between multiple `CropAspectItem`.
* Option to the `Transform` tool to reset the configurations.
* Option to the `Brush` tool to delete the layer.
* Option to the `Adjustment` tool to reset the configurations.
* Indicator line for the linear blur horizon.
* Visual gab at the snap point of the seek slider.

### Changed
* Default tool order so that the Focus tool is next to the Adjust tool.
* Default order and availability of options in `StickerOptionPanel`, `TextOptionToolPanel` and `TextDesignOptionToolPanel`.
* Styling of `CropAspectItem`, `FilterItem`, `AdjustOption`, `StickerCategoryItem`, `TextDesignItem`, `OverlayItem`, `FrameItem` and `FocusOption`.
* Overlay item toggle through the blend modes on click.
* Multiple icons were replaced across the SDK.
* None overlay item shows image preview instead of icon.

### Breaking-Changes
* The layout has been changed in some details. We offer a compatibility package to support the old layout files we provided in the demo repository.

* Drawable with name `imgly_icon_focus_center_thump` is renamed to `imgly_icon_focus_center_thumb`.

* We have corrected small cross platform deviations of the color
** String with name "pesdk_color_item_00000000" is renamed to "pesdk_common_title_transparentColor"
** String with name "pesdk_color_item_FFFFFFFF" is renamed to "pesdk_common_title_whiteColor"
** String with name "pesdk_color_item_FF7E7E7E" is renamed to "pesdk_common_title_grayColor"
** String with name "pesdk_color_item_FF000000" is renamed to "pesdk_common_title_blackColor"
** String with name "pesdk_color_item_FF30E5E7" is renamed to "pesdk_common_title_lightBlueColor"
** String with name "pesdk_color_item_FF6784FF" is renamed to "pesdk_common_title_blueColor"
** String with name "pesdk_color_item_FF8B60FF" is renamed to "pesdk_common_title_purpleColor"
** String with name "pesdk_color_item_FFE161FF" is renamed to "pesdk_common_title_orchidColor"
** String with name "pesdk_color_item_FFFF64CE" is renamed to "pesdk_common_title_pinkColor"
** String with name "pesdk_color_item_FFFF6688" is renamed to "pesdk_common_title_redColor"
** String with name "pesdk_color_item_FFE74E49" is renamed to "pesdk_common_title_orangeColor"
** String with name "pesdk_color_item_FFF4744D" is renamed to "pesdk_common_title_goldColor"
** String with name "pesdk_color_item_FFFFCD62" is renamed to "pesdk_common_title_yellowColor"
** String with name "pesdk_color_item_FFC8FF5F" is renamed to "pesdk_common_title_oliveColor"
** String with name "pesdk_color_item_FF7EFF60" is renamed to "pesdk_common_title_greenColor"
** String with name "pesdk_color_item_FF42FFDC" is renamed to "pesdk_common_title_aquamarinColor"
** String with name "pesdk_color_item_FF3FFF84" is now unused and removed. 

## v6.0.9

### Fixed
* NullPointerException while changing text configuration.

## v6.0.8

### Fixed
* ArithmeticException in some cases while using dynamic frames.

## v6.0.7

### Fixed
* The overlay intensity was ignored by some blend modes.

## v6.0.6

### Fixed
* Unknown JPEG type cause Crash

## v6.0.5

### Added
* Adjustments: temperature, whites and blacks

### Fixed
* Crash after export if watermark is enabled.
* Some classes are not protected against proguard, which cause in runtime crashes.
* The title bar and therefore the confirm button of the Text and Text Design Tool is not reachable on some phones with a "notch".
* Export of Images with transparency is sometimes not correct. 

### Known issues
* We are currently working on AndroidX support. Due to a bug in the Android RenderScript Support libraries we are actively looking into a workaround. 

## v6.0.4

### Added
* The SDK can now be used for testing purposes without unlocking it with a license. A watermark image will be displayed above the edited photo in such cases. (Simply remove `licencePath` from `pesdkConfig`)

## v6.0.3

### Fixed
* Stickers are sometimes blurry or do not load.

## v5.1.8

### Fixed
* Fix compiler error in newer gradle versions.
* Stickers are sometimes blurry or do not load.

## v6.0.2

### Fixed
* Crash with some png color formats.
* Plugin does not support `kotlin-kapt`.
* Preview quality is bad on some pictures.
* Latest text state (Colors and Font) is not restored.

## v5.1.7

### Fixed
* Crash with some png color formats.

## v6.0.1

### Changed
* You can remove `pesdkVersion` from the `pesdkConfig` in you gradle config, the default value is now the plugin version.

### Fixed
* Missing ForceCrop option.
* Missing ForceCrop Guides on the Page.
* Missing `clear()` options for the assets.
* Missing Kotlin dependency.
* Crash without troubleshoot info, while creating the SettingList()

## v6.0.0

__PLEASE LOOK AT THE NEW DOCUMENTATION MANY THINGS HAS CHANGED__

### Added
* Licenced new feature "TextDesign", get a trail licence to test it.
* Background exporting without UI.
* Gradle integration plugin.
* You can create and start the Editor with different Themes.
* Two new Focus Modes.
* Gamma adjustment.

### Changed
* Java version to 1.8.
* Android SDK to 27.
* Use of Gradle 3.1.3 build plugin for faster builds.
* Split features into separate modules and asset packs, this makes it easier to control the sdk size.
* Refactored Layouts and styles.
* Rework of the focus tool with better single touch control.
* UI is now optional and can completely replaced by your own UI.
* The awkward tool class concept is removed, all functionality is now in the backend.
* You can now add your own operations (you need to understand OpenGl and Renderscript).
* Settings and state classes can be extended with your own code.

### Fixed
* Color mismatch at export.
* Some blurry in the focus area.
* Focus preview broken on some low end devices.
* Cross platform issues with the serialisation.
* `RecyclerView` sometimes scrolls in the wrong position.

## v5.1.5

### Fixed
* SDK crashes if there is a missing font in the serialisation.
* Radial focus creates an oval blur on non-rectangular images.
* ArithmeticException if device's display is too small like on smartwatches.

## v5.1.4

### Fixed
* Alpha blending produces dark glow.
* Clarity adjustment produces dark glow.

## v5.1.3

### Added
* Option `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT = false` to prevent the Text Sticker bounding box from resizing when the font is changed.

## v5.1.2

### Fixed
* `CameraSettings.setOpenEditorAfterCapture` flag is not working.
* `SOURCE_IMAGE_URI` is `null` if editor is canceled.
* NullPointerException when input image is `null`.

## v5.1.1

### Fixed
* Radial and linear blur do not work on some devices.

## v5.1.0

### Fixed
* SDK crashes if `ImageSource` source file is deleted while decoding (ArithmeticException).
* Editor can be opened twice.
* Serialized stickers are sometimes broken or missing after loading.
* Add `onAttachedToList()` and `onDetachedFromList()`to the `DataSourceViewHolder`
* Add URI Support `EditorLoadSettings.setImageSource()`

### Breaking changes
* `EditorLoadSettings.setImageSourcePath()`  is deprecated use `EditorLoadSettings.setImageSource()` instead.
* `Intent.getStringExtra(ImgLyIntent.SOURCE_IMAGE_PATH)` is deprecated use `Uri source = Intent.getParcelableExtra(ImgLyIntent.SOURCE_IMAGE_URI);` instead.
* `Intent.getStringExtra(ImgLyIntent.RESULT_IMAGE_PATH)` is deprecated use `Uri result = Intent.getParcelableExtra(ImgLyIntent.RESULT_IMAGE_URI);` instead.

## v5.0.22

### Fixed
* Frame list does not live update when config has changed.

### Added
* Optional text buttons for the action bar instead of icon buttons.

## v5.0.21

### Fixed
* Undoing brush step does not work after changing color.

## v5.0.20

### Hot-Fixed
* Possible crash while revert text history

## v5.0.19

### Fixed
* Three of the default asset sticker shapes are missing after gradle 3.0 compiling.

## v5.0.18

### Fixed
* Static frames are not loaded by the deserialization.

## v5.0.17

### Fixed
* Internal class TransformedMotionEvent creates wrong states in some cases.
* Sticker matrix updated wrong which causes flickering.
* Exporting many stickers crashes in some cases.
* Image does not export if only the crop aspect has changed.
* Sticker flickering while resizing on some devices.
* Texture Memory is not free up while using with results in slightly slow down.

## v5.0.16

### Hotfix
* Multitouch gesture is unstable.

### Fixed
* Slider does not close when clicking "To Front" in the Brush tool.

## v5.0.15 __DEPRECATED!__

### Fixed
* A 90 degree turn is not possible if no suitable aspect is found.
* The "Gallery" button is sometimes not visible.
* Layout problems with "right-to-left" languages.

## v5.0.14

### Fixed
* Images not saved if only text is added.

## v5.0.13

### Fixed
* Undoing brush steps reverts brush hardness, color and size.
* Increasing the text size over the limit, increases the bounding box width.
* Brush ignores transformation.
* Typo in filter name.
* OpenGl vertex index out of range error.
* Renderscript crash while export on API 26.
* Exception "Only one Looper may be created per thread".
   * 🚨 This is a breaking change if you use the ThreadUtils.
* Text is added upside down, if the image is flipped.

## v5.0.12 __DEPRECATED!__

### Fixed
* Frame image tiles are repeating incorrectly.
* Frame tiles resolution is too low when frame is stretched.
* Brush is in the wrong position after exporting the image.
* Undoing brush steps creates artifacts when the image is too big.
* Undoing brush steps creates artifacts when the image preview width is not divisible by eight.
* Taking picture with front camera adds wrong exif rotation.
* Wrong exif information handling, when exporting image, which adds a black stripe in some cases.
* Wrong threading which freezes background gl threads.
* Text cursor is not at the end of the text when text is re-edited.
* License issues.

### Changed
* Performance improved.

## v5.0.11

### Fixed
* Artifacts on some devices with non-standard compliant OpenGL ES 2.0 implementation.
* NullPointerException, if image loading interrupts because of a broken file stream.
* Brush history produces color mismatch.
* Sticker is being rotated incorrectly when the image is mirrored

## v5.0.9

### Fixed
* Overlay repeats after exporting but it shouldn't.
* Crash while writing serialization, if the frame feature is disabled by license.

## v5.0.8

### Fixed
* Image do not reload after change it.

## v5.0.7

### Fixed
* Crashes when opening the brush tool while touching the canvas.
* Doesn't loading the picture when open the frame and transform tool while touching the canvas.

### Changed
* Support for images smaller than 32px per side.

## v5.0.6

### Fixed
* Image export is truncated in special cases with exif rotation 8 (Viewport 270 CW)

## v4.1.6

### Fixed
* Image export is truncated in special cases with exif rotation 8 (Viewport 270 CW)

## v5.0.5

### Changed
* Changed crop ratio name to 'Free'.
* Set rotation control dots invisible behind the value.
* Set item label to blue if the item is selected.

### Fixed
* Flipped image rotates in the wrong direction.
* Sticker not loading.

## v5.0.4

### Changed
* Add `ADJUSTMENT_OPTIONS` to Sticker Config, allow the user the change brightness, contrast and saturation of a sticker.  

## v5.0.3

### Fixed
* Fix issues with loading gif and bmp images.
* Licensing issues.
* Filter always uses the placeholder photo. 
* Image not centered by using a custom layout.

## v5.0.2

### Fixed
* The save policy are handled correct now.
* OpenGL Error after deserialize.

### Not fixable at the moment
* Deserialization / Rendering of text is not platform independent.

## v5.0.1-RC1

**This is pre release version and can have some bugs**

### Fixed
* Problems with Android 8.0

### Changed
* Performance improvements. Preview of Brush, Sticker and Text are now extremely fast on most of the devices.
* Layer are now rendered in preview with OpenGL

### Not fixable at the moment
* Deserialization / Rendering of text is not platform independent.

## v5.0.0-beta

### Beta-Release-Notes

**This is a beta version and has some known bugs**
* Android 8.0.0 seams to have an issue with TextureView, this view is used by brush and cause display issues in preview.
  This Bug is also happen in older SDK Versions, we will hat to fix it later by changing our general implementation.
* Deserialization of text is not platform independent.
* The save policy are currently not correct.
* PNG and GIF can crash if the image is to big.

### Added
* GIF loading support for the first frame and exported as PNG or JPEG.
* Global History for all Tools, Local History for some tools.
* Support for serialization and deserialization with json schema v3.0.0 (platform independent, but with some issues).
* The background color of the editor stage is now adjustable in the LayerListSettings.
* The background color of the camera stage is now adjustable in the CameraSettings.

### Changed
* Version 2.1 of the Licence is now required. 
* Assets now must have an unique id for the serialisation.
* Method AbstractEditorTool.isRevertible() is now deprecated, please use isCancelable() and isAcceptable() instead.
* Rename imgly_icon_download to imgly_icon_save.
* Transform Tool now operates like iOS.
* Event dispatcher is now Synchronized.
* Object recycling for better performance and better thread stability.
* New thread handler for better performance and better thread stability.

### Fixed
* Errors with the Event dispatcher.
* Randomly icon change when using VectorDrawables.
* VectorDrawable display issues.
* Some short bugs.
* Add two missing default ColorFilters.

## v4.1.5

### Fixed
* Overlay with transparency looks wrong after export.
* Transform tool does not show the image, if an overlay is applied.
* Crash with NullPointerException, when call StateHandler.hasChanges().
* Crash with NullPointerException("BitmapRegionDecoder.getWidth() on a null object reference") when image format not support by tile decoder.
* Crash with IllegalStateException("child already has a parent").

### Known Bugs, but not fixable without API changes (please wait for upcoming v5.0).
* Android 8.0 has display issues while Brush (Flickering for 1 Frame).
* Some, issues with async events.
* Sometimes, the dismiss dialog is displayed without changes.

## v4.1.4

### Changed
* Remove unused ImgLyTitleBar reference. 

### Fixed
* Brush default color is transparent.

## v4.1.3

### Fixed
* Dismiss edits dialog appears without any changes.

## v4.1.2

### Changed
* Add Ability to configure existing panel options by extend panels. Simply extend a panel like `AdjustmentToolPanel` and override the `createOptionList` (returns the icons in the list) or `createQuickOptionList` (returns the icon inside the image area) method.

## v4.1.1

### Changed
* Configuration changes in PESDKConfig during the editor is open, are now updated instantly in the UI.

### Fixed
* Icon color of some icons is wrong if you change `@color/imgly_icon_color`.

## v4.1.0

### Changed
* Multiline Text feature like iOS.
* Sticker category keep the last selection.

### Fixed
* Preview of transparent images is wrong.

## v4.0.2

### Fixed
* Workaround: RenderScript crashes on Nexus devices with Android 7.1
* NullPointer exception, by some camera modules
* NullPointer exception, by a not reproduceable behavior.
* Linear Focus handles, rotate in the wrong direction if the image is flipped.
 
## v4.0.1

### Fixed
* Overlays sometimes not changed.
* Native crash on Android <= 5.0 devices, because of a memory leak inside Googles Vector Drawable support library (This is not our fault but we have found a Workaround).
* The dismiss dialog appears, without any changes.

## v4.0.0

### Changed
* OpenGl Preview for blazing fast filter preview.
* Other Performance Improvements.
* Memory Improvements.
* An improved Focus Filter.
* Smaller SDK Size
* The preview image is now automatically resized when a slider overlays the preview at the bottom, so that is always completely visible.
* Small improvements

### Added
* Tool "Overlay".
* GPS Tag Support for the Camera.
* Pinch and Zoom in Main View and Brush Tool.
* PNG export Support.

### Fixed
* A lot of bug fixes.

## v3.1.1

### Changed
* Remove old Fonts an add improved fonts.

## v3.0.15

### Fixed
* Camera crash on Androids with API lower or equal 20.
* Cutting Frames on some image size, aspect ratio combination.

## v3.0.12

### Fixed
* Small bugfixes.

## v3.0.11

### Fixed
* When dismiss dialog appears, you can still make changes to the image.
* For each time the cancel button is pressed, one new dismiss dialog is rendered on screen.
* You can also still make changes to the image during the image export.
* Icons in some special cases in wrong order.
* Sticker straighten action if the image itself is rotated.

### Changed
* Layout imgly_popup_confirm.xml has changed and is renamed to `imgly_popup_confirm_dialog.xml`.
* Layout imgly_activity_photo_editor.xml has changed!

## v3.0.10

### Fixed
* Sticker list do not scroll to top if you change the category.

## v3.0.9

### Fixed
* Dismiss edits dialog appears without any changes.
* ImageDecoder crash in some constellations.
* SettingsList configurations randomly ignored.
* Force-Crop not working.
* AutoRotation is sometimes wrong.
* License validation sometimes take to long.

## v3.0.7

### Fixed
* Missing Linter Warning if you use Documents until API 19 and fix crash if you ignore this warning and use it until API 19.
* Toggled list images if you use vector and rastered drawables together.

### Changed
* Show a warning and fix wrong scaled frames, if you put the frame assets in the wrong drawable directory.
