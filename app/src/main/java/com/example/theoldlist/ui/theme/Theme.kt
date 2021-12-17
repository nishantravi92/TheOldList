package com.example.theoldlist.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.theoldlist.settings.PreferenceUtils


private val DarkColorPalette: Colors
    @Composable
    @ReadOnlyComposable
    get() = darkColors(
        primary = ColorPalette.red_dark,
        onPrimary = ColorPalette.white,
        secondary = ColorPalette.black_50_opacity,
        onSecondary = ColorPalette.black,
        background = ColorPalette.background_dark
    )


private val LightColorPalette: Colors
    @Composable
    @ReadOnlyComposable
    get() = lightColors(
        primary = ColorPalette.red_light,
        onPrimary = ColorPalette.black,
        secondary = ColorPalette.white_50_opacity,
        onSecondary = ColorPalette.white,
        background = ColorPalette.background_light
    )

@ExperimentalComposeUiApi
@Composable
fun TheOldListTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
    ) {
        Box {
            val image = painterResource(PreferenceUtils.currentlySetWallpaper)
            Image(
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = "Background image",
                modifier = Modifier.fillMaxSize()
            )
            content()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun PlainBackgroundMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
    ) {
        content()
    }
}

