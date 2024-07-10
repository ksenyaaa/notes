package ru.notes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val title = intent?.extras?.getString("ARG1")
            val text = intent?.extras?.getString("ARG2")
            val id = intent?.extras?.getInt("ARG3")
            val notification = Notification(it)
            notification.showNotification(
                if (title.isNullOrEmpty()) "Загляни в приложение" else title,
                if (text.isNullOrEmpty()) "Не пропусти свои планы" else text,
                id
            )
        }
    }
}


