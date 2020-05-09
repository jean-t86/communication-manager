package com.nimble.communicationmanager.carriers.snackbar

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.EnvelopeDSL

@Suppress("DataClassPrivateConstructor")
data class SnackbarEnvelope private constructor(
    override val message: String,
    val duration: Duration,
    val view: View,
    val action: Action?
) : Envelope {
    @EnvelopeDSL
    class Builder {
        private var message = ""
        private var duration = Duration.LONG
        private lateinit var view: View
        private var action: Action? = null

        fun message(lambda: () -> String) {
            this.message = lambda()
        }

        fun duration(lambda: () -> Duration) {
            this.duration = lambda()
        }

        fun view(lambda: () -> View) {
            this.view = lambda()
        }

        fun action(lambda: () -> Action) {
            this.action = lambda()
        }

        fun seal() =
            SnackbarEnvelope(
                message,
                duration,
                view,
                action
            )
    }

    data class Action(
        val text: String,
        val action: View.OnClickListener
    )

    enum class Duration(val value: Int) {
        SHORT(Snackbar.LENGTH_SHORT),
        LONG(Snackbar.LENGTH_LONG)
    }
}
