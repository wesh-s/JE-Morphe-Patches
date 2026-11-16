package app.morphe.patches.je

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.morphe.patcher.methodCall
import app.morphe.patcher.patch.patch

internal object ActivityOnCreateFingerprint : Fingerprint(
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    filters = listOf(
        methodCall(smali = "Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V"),
    ),
)

@Suppress("unused")
val bytecodeLabPatch = patch(
    name = "Bytecode Lab (No-Op Hook)",
    description = "Educational bytecode patch that locates Activity.onCreate via Fingerprint and injects a safe nop instruction.",
    default = false,
) {
    execute {
        ActivityOnCreateFingerprint.method.addInstructionsWithLabels(
            0,
            """
                nop
            """,
        )
    }
}
