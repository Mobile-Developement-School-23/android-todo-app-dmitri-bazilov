package com.dmitri.yandex_tasks.network

import android.content.Context
import java.util.UUID

class NetworkUtil(context: Context) {

    private val REVISION = "curRev"
    private val DEVICE = "curDev"

    private val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    private val preferencesEditor = preferences.edit()

    init {
        if (preferences.getString(DEVICE, null) == null) {
            preferencesEditor.putString(DEVICE, UUID.randomUUID().toString())
            preferencesEditor.apply()
        }
    }

    fun getRevision(): Int = preferences.getInt(REVISION, 0)

    fun setRevision(value: Int) {
        preferencesEditor.putInt(REVISION, value)
        preferencesEditor.apply()
    }

    fun getDeviceId(): String = preferences.getString(DEVICE, "")!!

}