package com.nimble.communicationmanager.carriers.toast

import android.widget.Toast
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.EnvelopeDSL

@Suppress("DataClassPrivateConstructor")
data class ToastEnvelope private constructor(
    override val message: String,
    val duration: Duration,
    val gravity: Gravity?
) : Envelope {
    @EnvelopeDSL
    class Builder {
        private var message = ""
        private var duration = Duration.LONG
        private var gravity: Gravity? = null

        fun message(lambda: () -> String) {
            this.message = lambda()
        }

        fun duration(lambda: () -> Duration) {
            this.duration = lambda()
        }

        fun gravity(lambda: () -> Gravity) {
            this.gravity = lambda()
        }

        fun seal() =
            ToastEnvelope(
                message,
                duration,
                gravity
            )
    }

    enum class Duration(val value: Int) {
        SHORT(Toast.LENGTH_SHORT),
        LONG(Toast.LENGTH_LONG)
    }

    data class Gravity(val gravity: Int, val xOffset: Int, val yOffset: Int)
}
