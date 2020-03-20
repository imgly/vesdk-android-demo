# VideoEditor SDK - Changelog

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
* Loading frame serialization can cause a crash.

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
