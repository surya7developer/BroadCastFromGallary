package com.suresh.broadcast.receiver.example.main

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.suresh.broadcast.receiver.example.R
import com.suresh.broadcast.receiver.example.broadcast.MyBroadcastListener
import com.suresh.broadcast.receiver.example.broadcast.MyBroadcastReceiver
import com.suresh.broadcast.receiver.example.databinding.ActivityMainBinding
import com.suresh.broadcast.receiver.example.image.ImageActivity
import com.suresh.broadcast.receiver.example.text.TextActivity
import com.suresh.broadcast.receiver.example.util.*
import com.suresh.broadcast.receiver.example.video.VideoActivity


class MainActivity : AppCompatActivity(), MyBroadcastListener {

    private lateinit var binding: ActivityMainBinding
    private var myBroadcastReceiver: MyBroadcastReceiver = MyBroadcastReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initt()
        getBroadCastIntent()
        intentFiltter()
    }

    private fun initt() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun intentFiltter() {

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_CALL)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED)
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myBroadcastReceiver, intentFilter)

    }

    private fun getBroadCastIntent() {

        if (intent.action != null && intent.type != null) {
            intent.type?.let {

                if (it.contains(VIDEO)) {

                    val inte = Intent(this, VideoActivity::class.java)
                    inte.putExtra(VIDEO, getUr().toString())
                    startActivity(inte)
                    finish()


                } else if (it.contains(IMAGE)) {

                    val inte = Intent(this, ImageActivity::class.java)
                    inte.putExtra(IMAGE, getUr().toString())
                    startActivity(inte)
                    finish()

                } else if (it.contains(TEXT)) {
                    val inte = Intent(this, TextActivity::class.java)
                    inte.putExtra(TEXT, intent.getStringExtra(Intent.EXTRA_TEXT))
                    startActivity(inte)
                    finish()
                } else {
                    showToast(getString(R.string.type_not_supported))
                }
            }
        }
    }

    private fun getUr(): Uri? {
        return intent.parcelable(Intent.EXTRA_STREAM)
    }

    override fun onActionName(string: String) {
        binding.txtActionName.text = string
    }

    override fun onCall() {
        showSnackBar(getColor(R.color.green), status = "Call")
    }

    override fun onScreenOn() {
        showSnackBar(getColor(R.color.green), status = "Screen On")
    }

    override fun isConnected() {
        showSnackBar(getColor(R.color.green), status = "Back to online")
    }

    override fun onBatteryLow() {
        showSnackBar(status = "Battery Low")
    }

    override fun onAirPlainMode() {
        showSnackBar(status = "Air Plain Mode")
    }

    override fun isNotConnected() {
        showSnackBar()
    }

    override fun isPowerConnected() {
        showSnackBar(getColor(R.color.green), status = "Charger Connected")
    }

    override fun isPowerDisconnected() {
        showSnackBar(status = "Charger Disconnected")
    }

    override fun onConfigurationChanges() {
        showSnackBar(status = "Configuration Changes")
    }


    fun Context.showSnackBar(
        bgColor: Int = getColor(R.color.red),
        status: String = "You're Offline",
    ) {
        Snackbar.make(binding.layMain, status, Snackbar.LENGTH_LONG)
            .setAction("Ok", { })
            .setActionTextColor(Color.WHITE)
            .setBackgroundTint(bgColor)
            .setTextColor(Color.WHITE)
            .show()
    }

    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

}