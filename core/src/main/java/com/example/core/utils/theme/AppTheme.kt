package com.example.core.utils.theme

import androidx.annotation.StringRes
import com.example.core.R

enum class ApplicationThemes(@StringRes val typeNameId: Int) {
    DAY(R.string.settings_theme_day_label),
    NIGHT(R.string.settings_theme_night_label),
    AUTOMATICALLY(R.string.settings_theme_auto_label),
}