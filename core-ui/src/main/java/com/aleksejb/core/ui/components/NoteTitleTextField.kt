package com.aleksejb.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteTitleTextField(
    title: String,
    onTitleChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = title,
        onValueChange = { onTitleChanged(it) }
    )
}