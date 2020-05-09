package com.nimble.communicationmanager.carriers.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.nimble.communicationmanager.Envelope
import com.nimble.communicationmanager.carriers.Carrier

class NotificationCarrier : Carrier {

    override fun send(context: Context, envelope: Envelope) {
        envelope as NotificationEnvelope
        val notificationsManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationsChannel(notificationsManager, envelope.channel)

        when (envelope.carrierAction) {
            NotificationEnvelope.CarrierAction.SEND -> sendNotification(
                context,
                envelope,
                notificationsManager
            )
            NotificationEnvelope.CarrierAction.CANCEL -> cancelNotification(
                envelope,
                notificationsManager
            )
        }
    }

    private fun sendNotification(
        context: Context,
        envelope: NotificationEnvelope,
        notificationsManager: NotificationManager
    ) {
        val notification = buildNotification(context, envelope)
        notificationsManager.notify(envelope.id, notification)
    }

    private fun cancelNotification(
        envelope: NotificationEnvelope,
        notificationsManager: NotificationManager
    ) {
        notificationsManager.cancel(envelope.id)
    }

    private fun buildNotification(context: Context, envelope: NotificationEnvelope): Notification {
        return NotificationCompat.Builder(context, envelope.channel.id).apply {
            setContentTitle(envelope.message)
            setContentText(envelope.contentText)
            setSmallIcon(envelope.smallIcon)
            envelope.contentIntent?.let {
                setContentIntent(it)
                setAutoCancel(true)
            }
            envelope.bigPicture?.let {
                setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(it)
                        .setBigContentTitle(envelope.message)
                )
            }
            envelope.action?.let {
                addAction(
                    it.icon,
                    it.title,
                    it.pendingIntent
                )
            }
            envelope.deleteIntent?.let {
                setDeleteIntent(it)
            }
            priority = NotificationCompat.PRIORITY_HIGH
            setDefaults(NotificationCompat.DEFAULT_ALL)
        }.build()
    }

    private fun createNotificationsChannel(
        notificationsManager: NotificationManager,
        channel: NotificationEnvelope.Channel
    ) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channel.id,
                channel.name,
                channel.importance
            ).apply {
                enableLights(channel.enableLights)
                lightColor = channel.lightColor
                enableVibration(channel.enableVibration)
                description = channel.description
            }
            notificationsManager.createNotificationChannel(notificationChannel)
        }
    }
}
