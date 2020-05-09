package com.nimble.communicationmanager.carriers.toast

import android.content.Context
import android.widget.Toast
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.carriers.Carrier

class ToastCarrier : Carrier {

    override fun send(
        context: Context,
        envelope: Envelope
    ) {
        envelope as ToastEnvelope
        val toast = Toast.makeText(context, envelope.message, envelope.duration.value)
        envelope.gravity?.let {
            toast.setGravity(
                envelope.gravity.gravity,
                envelope.gravity.xOffset,
                envelope.gravity.yOffset
            )
        }
        toast.show()
    }
}
