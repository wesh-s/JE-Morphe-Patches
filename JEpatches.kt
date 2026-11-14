// IMPORTANT: Keep the exact package line that matches your folder path!
// Check the existing sample .kt file in your folder and copy its top package line here.
package app.morphe.patches.JEpatches.kt

import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element

@Suppress("unused")
val allowBackupPatch = resourcePatch(
    name = "Enable App Data Backup",
    description = "Sets android:allowBackup to true in AndroidManifest.xml so local app data can be backed up."
) {
    // Replace this with the real Android Package ID of the app you want to patch
    compatibleWith("com.example.targetapp")

    execute {
        // 1. Open the decoded AndroidManifest.xml inside the APK
        document("AndroidManifest.xml").use { document ->
            // 2. Locate the <application> element
            val applicationNode = document
                .getElementsByTagName("application")
                .item(0) as Element

            // 3. Add or overwrite the android:allowBackup attribute
            applicationNode.setAttribute("android:allowBackup", "true")
        }
    }
}


