package com.nimble.communicationmanager.carriers.console

import android.content.Context
import android.util.Log
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.carriers.Carrier

class ConsoleCarrier :
    Carrier {
    override fun send(context: Context, envelope: Envelope) {
        Log.d("ConsoleCarrier", envelope.message)
    }
}
