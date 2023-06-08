package com.aleksejb.ui.checkbox.note

import com.aleksejb.core.domain.model.CheckboxItem

data class CheckboxNoteState(
    val title: String = "Title",
    val items: List<CheckboxItem> = emptyList()
)