package com.nimble.communicationmanager.carriers.snackbar

import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.carriers.Carrier

class SnackbarCarrier :
    Carrier {
    override fun send(context: Context, envelope: Envelope) {
        envelope as SnackbarEnvelope
        with(
            Snackbar.make(
                envelope.view,
                envelope.message,
                envelope.duration.value
            )
        ) {
            envelope.action?.let {
                setAction(it.text, it.action)
            }
            show()
        }
    }
}
