# VideoEditor SDK - Changelog
## v7.0.7

### Added
* Option for the user to choose a sticker from your gallery, add `new PersonalStickerAddItem()` to your StickerList config to enable it.

### Fixed
* Serialization is written with wrong sizes if the image rotation is adjusted.

## v7.0.6

### Fixed
* Some focus operations are not correctly serialized.
* Frame disappears after accepting in frame tool panel.
* Color pipette picks are not reverted after cancel.
* NPE if brush is not part of the license feature list.
* Tool list can't be changed.
* migration task crash with `No signature of method: java.lang.String.findIndexOf()` 

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
