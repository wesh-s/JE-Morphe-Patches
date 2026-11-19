package app.morphe.patches.je

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.morphe.patcher.methodCall
import app.morphe.patcher.patch.patch

internal object CheckTrialStatusFingerprint : Fingerprint(
    returnType = "I",
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        methodCall(
            smali = "Lcom/pairip/licensecheck/LicenseClient;->checkTrialStatus()Lcom/pairip/licensecheck/TrialStatus;",
        ),
    ),
)

internal object StopTrialFingerprint : Fingerprint(
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        methodCall(
            smali = "Lcom/pairip/licensecheck/LicenseClient;->stopTrial(Landroid/content/Context;)V",
        ),
    ),
)

@Suppress("unused")
val trialClientExperimentPatch = patch(
    name = "TrialClient Experiment",
    description = "Returns NOT_UNDER_TRIAL (2) for TrialClient.checkTrialStatus and disables stopTrial.",
    default = false,
) {
    execute {
        CheckTrialStatusFingerprint.method.addInstructionsWithLabels(
            0,
            """
                const/4 v0, 0x2
                return v0
            """.trimIndent(),
        )

        StopTrialFingerprint.method.addInstructionsWithLabels(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}

