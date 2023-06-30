package com.suresh.broadcast.receiver.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.suresh.broadcast.receiver.example.util.isConnected


class MyBroadcastReceiver(private var listener: MyBroadcastListener) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        listener.onActionName(intent?.action.toString())

        when (intent?.action) {

            Intent.ACTION_CALL->{
                listener.onCall()
            }
            Intent.ACTION_BATTERY_LOW->{
                listener.onBatteryLow()
            }
            Intent.ACTION_SCREEN_ON->{
                listener.onScreenOn()
            }
            Intent.ACTION_CONFIGURATION_CHANGED->{
                listener.onConfigurationChanges()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED-> {
                listener.onAirPlainMode()
            }
            Intent.ACTION_POWER_CONNECTED -> {
                listener.isPowerConnected()
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                listener.isPowerDisconnected()
            }
            ConnectivityManager.CONNECTIVITY_ACTION -> {
                if (context.isConnected()) {
                    listener.isConnected()
                } else {
                    listener.isNotConnected()
                }
            }
        }
    }
}