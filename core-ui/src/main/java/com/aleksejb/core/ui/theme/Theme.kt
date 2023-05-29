package com.aleksejb.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        shapes = AppShapes,
        typography = Typography,
        content = content
    )
}