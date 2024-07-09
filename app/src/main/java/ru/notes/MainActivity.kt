package ru.notes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var controller: NavController? = null
    private var binding: ActivityMainBinding? = null
    private var notification: Notification? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        controller = (supportFragmentManager.findFragmentById(R.id.container) as
                NavHostFragment).navController
        controller?.let { navController ->
            binding?.bottomNav?.setupWithNavController(navController)
        }
        notification = Notification(this)
        notification?.showNotification(title = "hi", text = "muy")
    }

    override fun onDestroy() {
        super.onDestroy()
        notification = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        controller?.navigateUp()
    }

}