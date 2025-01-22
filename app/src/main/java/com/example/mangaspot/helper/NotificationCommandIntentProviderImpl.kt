package com.example.mangaspot.helper

import android.content.Context
import android.content.Intent
import com.example.mangaspot.main.MainActivity
import com.example.mangaspot.model.NotificationControlCommand

class NotificationCommandIntentProviderImpl : NotificationCommandIntentProvider {
    override fun getOpenCameraDetailsIntent(context: Context, cameraId: Int): Intent {
        return Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(NotificationControlCommand.ACTION_OPEN_CAMERA_BY_ID, cameraId)
        }
    }

    override fun getOpenSomeProfileDetailsIntent(context: Context, cameraId: Int): Intent {
        return Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(NotificationControlCommand.ACTION_OPEN_SOME_CAMERA_BY_ID, cameraId)
        }
    }

    override fun getOpenDashboardCameraDetailsIntent(context: Context, cameraId: Int): Intent {
        return Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(NotificationControlCommand.ACTION_OPEN_DASHBOARD_BY_ID, cameraId)
        }
    }
}