package com.nimble.communicationmanager.carriers.notification

import android.app.PendingIntent
import android.graphics.Bitmap
import android.graphics.Color
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.EnvelopeDSL
import com.nimble.communicationmanager.R

@Suppress("DataClassPrivateConstructor")
data class NotificationEnvelope private constructor(
    override val message: String,
    val id: Int,
    val channel: Channel,
    val contentText: String,
    val smallIcon: Int,
    val contentIntent: PendingIntent?,
    val carrierAction: CarrierAction,
    val bigPicture: Bitmap?,
    val action: Action?,
    val deleteIntent: PendingIntent?
) : Envelope {
    @EnvelopeDSL
    class Builder {
        private var message = ""
        private var id: Int = 0
        private var channel = Channel()
        private var contentText: String = ""
        private var smallIcon: Int = R.drawable.ic_android
        private var contentIntent: PendingIntent? = null
        private var carrierAction = CarrierAction.SEND
        private var bigPicture: Bitmap? = null
        private var action: Action? = null
        private var deleteIntent: PendingIntent? = null

        fun message(lambda: () -> String) {
            this.message = lambda()
        }

        fun id(lambda: () -> Int) {
            this.id = lambda()
        }

        fun notificationChannel(lambda: () -> Channel) {
            this.channel = lambda()
        }

        fun contentText(lambda: () -> String) {
            this.contentText = lambda()
        }

        fun smallIcon(lambda: () -> Int) {
            this.smallIcon = lambda()
        }

        fun contentIntent(lambda: () -> PendingIntent) {
            this.contentIntent = lambda()
        }

        fun carrierAction(lambda: () -> CarrierAction) {
            this.carrierAction = lambda()
        }

        fun bigPicture(lambda: () -> Bitmap) {
            this.bigPicture = lambda()
        }

        fun action(lambda: () -> Action) {
            this.action = lambda()
        }

        fun deleteIntent(lambda: () -> PendingIntent) {
            this.deleteIntent = lambda()
        }

        fun seal() =
            NotificationEnvelope(
                message,
                id,
                channel,
                contentText,
                smallIcon,
                contentIntent,
                carrierAction,
                bigPicture,
                action,
                deleteIntent
            )
    }

    data class Channel(
        val id: String = PRIMARY_CHANNEL_ID,
        val name: String = " ",
        val importance: Int = Importance.DEFAULT.value,
        val enableLights: Boolean = true,
        val lightColor: Int = Color.RED,
        val enableVibration: Boolean = true,
        val description: String = " "
    )

    data class Action(
        val icon: Int,
        val title: String,
        val pendingIntent: PendingIntent
    )

    enum class Importance(val value: Int) {
        DEFAULT(3),
        HIGH(4),
        LOW(2),
        MAX(5),
        MIN(1),
        NONE(0),
        UNSPECIFIED(-1000)
    }

    enum class CarrierAction {
        SEND,
        CANCEL
    }
}

private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
