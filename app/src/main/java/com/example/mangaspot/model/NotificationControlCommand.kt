package com.example.mangaspot.model

import android.content.Intent

sealed class NotificationControlCommand {

    data object NoCommandNotification : NotificationControlCommand()
    data class OpenCameraDetailsCommandNotification(
        val cameraId: Int,
        val previewImage: String,
        val animationKey: String
    ) : NotificationControlCommand()

    data class OpenSomeProfileDetailsCommandNotification(
        val cameraId: Int,
        val previewImage: String,
        val animationKey: String
    ) : NotificationControlCommand()

    data class OpenDashboardDetailsCommandNotification(
        val cameraId: Int,
        val previewImage: String,
        val animationKey: String
    ) : NotificationControlCommand()

    companion object {
        const val ACTION_OPEN_CAMERA_BY_ID = "openCameraById"
        const val ACTION_OPEN_SOME_CAMERA_BY_ID = "openSomeCameraById"
        const val ACTION_OPEN_DASHBOARD_BY_ID = "openDashboardById"

        private const val EMPTY_VALUE = ""

        fun getNotificationCommand(intent: Intent): NotificationControlCommand {
            val camId = intent.getIntExtra(ACTION_OPEN_CAMERA_BY_ID, -1)
            if (hasCamera(camId)) {
                return OpenCameraDetailsCommandNotification(camId, EMPTY_VALUE, EMPTY_VALUE)
            }

            val someCameraId = intent.getIntExtra(ACTION_OPEN_SOME_CAMERA_BY_ID, -1)
            if (hasCamera(someCameraId)) {
                return OpenSomeProfileDetailsCommandNotification(
                    someCameraId,
                    EMPTY_VALUE,
                    EMPTY_VALUE
                )
            }

            val dashboardCameraId = intent.getIntExtra(ACTION_OPEN_DASHBOARD_BY_ID, -1)
            if (hasCamera(dashboardCameraId)) {
                return OpenDashboardDetailsCommandNotification(
                    dashboardCameraId,
                    EMPTY_VALUE,
                    EMPTY_VALUE
                )
            }

            return NoCommandNotification
        }

        private fun hasCamera(cameraId: Int) = cameraId >= 0
    }
}
