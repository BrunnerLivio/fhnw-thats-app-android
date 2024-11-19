package fhnw.emoba.thatsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fhnw.emoba.R

private val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk, FontWeight.Normal)
)

val typography = Typography(

    headlineLarge = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 40.sp,
        fontWeight    = FontWeight.Light,
        lineHeight    = 40.sp,
        letterSpacing = (-1.5).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 50.sp,
        fontWeight    = FontWeight.Light,
        lineHeight    = 50.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 40.sp,
        fontWeight    = FontWeight.Normal,
        lineHeight    = 40.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 22.sp,
        fontWeight    = FontWeight.ExtraBold,
        lineHeight    = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 24.sp,
        fontWeight    = FontWeight.SemiBold,
        lineHeight    = 29.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 20.sp,
        fontWeight    = FontWeight.SemiBold,
        lineHeight    = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 16.sp,
        fontWeight    = FontWeight.Normal,
        lineHeight    = 28.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 14.sp,
        fontWeight    = FontWeight.Medium,
        lineHeight    = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 12.sp,
        fontWeight    = FontWeight.Bold,
        lineHeight    = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SpaceGrotesk,
        fontSize      = 12.sp,
        fontWeight    = FontWeight.SemiBold,
        lineHeight    = 16.sp,
        letterSpacing = 1.sp
    )
)
