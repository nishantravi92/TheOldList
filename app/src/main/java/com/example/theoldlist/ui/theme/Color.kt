package com.example.theoldlist.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.theoldlist.R

object ColorPalette {

    val red_light:Color
        @Composable
        @ReadOnlyComposable
        get() = colorResource(id = R.color.red_light)

    val red_dark:Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0xffba000d)

    val white_50_opacity: Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0x80ffffff)

    val black_50_opacity:Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0x80000000)

    val background_dark:Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0xff121212)

    val background_light: Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0xffffffff)

    val white: Color
        @Composable
        @ReadOnlyComposable
        get() =  Color(0xffffffff)

    val black: Color
        @Composable
        @ReadOnlyComposable
        get() = Color(0xff000000)
}
