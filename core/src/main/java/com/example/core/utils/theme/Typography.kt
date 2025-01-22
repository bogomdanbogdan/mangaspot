package com.example.core.utils.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 12.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
    )
)