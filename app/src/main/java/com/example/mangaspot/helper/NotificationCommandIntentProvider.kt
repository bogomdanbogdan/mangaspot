package com.example.mangaspot.helper

import android.content.Context
import android.content.Intent

interface NotificationCommandIntentProvider {
    fun getOpenCameraDetailsIntent(context: Context, cameraId: Int): Intent
    fun getOpenSomeProfileDetailsIntent(context: Context, cameraId: Int): Intent
    fun getOpenDashboardCameraDetailsIntent(context: Context, cameraId: Int): Intent
}