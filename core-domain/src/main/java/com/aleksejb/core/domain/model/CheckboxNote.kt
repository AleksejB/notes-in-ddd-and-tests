package com.aleksejb.core.domain.model

data class CheckboxNote(
    val id: Int,
    val title: String,
    val items: List<CheckboxItem>
)