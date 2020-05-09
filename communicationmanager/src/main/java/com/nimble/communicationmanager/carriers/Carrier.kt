package com.nimble.communicationmanager.carriers

import android.content.Context
import com.nimble.communicationmanager.Envelope

interface Carrier {
    fun send(context: Context, envelope: Envelope)
}
