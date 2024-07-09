package ru.notes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull

class Notification (private val context: Context) {

    fun showNotification(title: String, text: String) {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        val sound: Uri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.notify)

        val intent = Intent(context, MainActivity::class.java)
        val pending = PendingIntent.getActivity(
            context, 100, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE,
        )

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, context.getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.drawable.baseline_edit_note_24)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(pending)
                .setLargeIcon(Bitmap.createBitmap(context.getDrawable(R.drawable.baseline_edit_note_24)?.toBitmapOrNull()!!))
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)


        NotificationChannel(
            context.getString(R.string.default_notification_channel_id),
            context.getString(R.string.default_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setSound(sound, audioAttributes)

            vibrationPattern = arrayOf(100L, 200L).toLongArray()
            lightColor = Color.BLUE
        }.also {
            notificationManager.createNotificationChannel(it)
        }

        notificationManager.notify(2132, builder.build())
    }

}