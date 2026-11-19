package app.morphe.patches.je

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.morphe.patcher.methodCall
import app.morphe.patcher.patch.patch

// 1. Fingerprint: tells Morphe HOW to find your target method in the APK
internal object MyTargetFingerprint : Fingerprint(
    returnType = "V", // Replace with the method's return type (V, Z, I, etc.)
    parameters = listOf(
        // Replace with the exact Smali parameter types inside ( ... )
        "Landroid/os/Bundle;",
    ),
    filters = listOf(
        // Copy a unique invoke-* line from inside the Smali method body
        methodCall(smali = "Lcom/example/app/SomeClass;->someHelper()V"),
    ),
)

// 2. Patch definition: tells Morphe WHAT to change once the method is found
@Suppress("unused")
val myCustomExperimentPatch = patch(
    name = "My Custom Experiment",
    description = "Describe clearly what this patch changes.",
    default = false,
) {
    execute {
        MyTargetFingerprint.method.addInstructionsWithLabels(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}

