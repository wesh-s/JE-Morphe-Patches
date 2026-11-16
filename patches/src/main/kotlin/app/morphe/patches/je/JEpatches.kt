package app.morphe.patches.je

import app.morphe.patcher.patch.resourcePatch
import app.morphe.patcher.patch.stringOption
import org.w3c.dom.Element

@Suppress("unused")
val allowBackupPatch = resourcePatch(
    name = "Allow Backup",
    description = "Sets android:allowBackup in AndroidManifest.xml for any target app.",
    default = false,
) {
    val allowBackupValue = stringOption(
        key = "allowBackupValue",
        default = "true",
        values = mapOf(
            "Enable backup (true)" to "true",
            "Disable backup (false)" to "false",
        ),
        title = "android:allowBackup value",
    )

    execute {
        document("AndroidManifest.xml").use { document ->
            val applicationNode = document.getElementsByTagName("application").item(0) as Element
            applicationNode?.setAttribute("android:allowBackup", allowBackupValue.value!!)
        }
    }
}

