package app.morphe.patches.je

import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element

@Suppress("unused")
val allowBackupPatch = resourcePatch(
    name = "Allow Backup",
    description = "Enables android:allowBackup in AndroidManifest.xml for any target app.",
    default = false,
) {
    execute {
        document("AndroidManifest.xml").use { document ->
            val applicationNode = document.getElementsByTagName("application").item(0) as? Element
            applicationNode?.setAttribute("android:allowBackup", "true")
        }
    }
}
