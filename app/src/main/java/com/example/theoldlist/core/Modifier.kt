package com.example.theoldlist.core

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.roundedShape(size: Dp = 2.dp): Modifier {
    return this.clip(
        RoundedCornerShape(size))
}