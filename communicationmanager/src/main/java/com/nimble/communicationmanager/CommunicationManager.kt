package com.nimble.communicationmanager

import android.content.Context
import com.nimble.communicationmanager.carriers.console.ConsoleCarrier
import com.nimble.communicationmanager.carriers.notification.NotificationCarrier
import com.nimble.communicationmanager.carriers.notification.NotificationEnvelope
import com.nimble.communicationmanager.carriers.snackbar.SnackbarCarrier
import com.nimble.communicationmanager.carriers.snackbar.SnackbarEnvelope
import com.nimble.communicationmanager.carriers.toast.ToastCarrier
import com.nimble.communicationmanager.carriers.toast.ToastEnvelope

class CommunicationsManager(private val context: Context) {
    fun communicate(envelope: Envelope) = when (envelope) {
        is ToastEnvelope -> ToastCarrier()
        is SnackbarEnvelope -> SnackbarCarrier()
        is NotificationEnvelope -> NotificationCarrier()
        else -> ConsoleCarrier()
    }.send(context, envelope)
}

interface Envelope {
    val message: String
}

@DslMarker
annotation class EnvelopeDSL

@Deprecated(
    "While Toast is currently still supported, prefer the use of Snackbar",
    ReplaceWith(
        "snackbarEnvelope",
        "com.jean.androidcertificate.communication.snackbarEnvelope"
    )
)
fun toastEnvelope(lambda: ToastEnvelope.Builder.() -> Unit) =
    ToastEnvelope.Builder().apply(lambda).seal()

fun snackbarEnvelope(lambda: SnackbarEnvelope.Builder.() -> Unit) =
    SnackbarEnvelope.Builder().apply(lambda).seal()

fun notificationEnvelope(lambda: NotificationEnvelope.Builder.() -> Unit) =
    NotificationEnvelope.Builder().apply(lambda).seal()
