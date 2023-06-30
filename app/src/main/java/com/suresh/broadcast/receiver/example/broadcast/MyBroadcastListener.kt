package com.suresh.broadcast.receiver.example.broadcast

interface MyBroadcastListener {

    fun onActionName(string: String)
    fun onCall()
    fun onScreenOn()
    fun isConnected()
    fun onBatteryLow()
    fun onAirPlainMode()
    fun isNotConnected()
    fun isPowerConnected()
    fun isPowerDisconnected()
    fun onConfigurationChanges()
}