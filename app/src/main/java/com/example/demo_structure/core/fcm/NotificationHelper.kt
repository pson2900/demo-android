package com.example.demo_structure.core.fcm

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demo_structure.screen.main.MainActivity
import com.example.demo_structure.R

class NotificationHelper(val context: Context) {
    var id = 1
    private fun createNotificationChannel(context: Context) {
        val name = "product X Channel"
        val descriptionText = "product X Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("my_channel_id", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Create and display a notification
    fun showNotification(bitmap: Bitmap?, title: String, message: String) {
        createNotificationChannel(context)
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, "my_channel_id")
            .setSmallIcon(R.drawable.ic_basic_information)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Set high priority
            .setFullScreenIntent(pendingIntent, true)
            .setAutoCancel(true)
        bitmap?.let {
            builder .setLargeIcon(bitmap)
        }
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            notify(id++, builder.build())
        }
    }
}