package com.aleksejb.ui.checkbox.note

sealed interface CheckboxNoteEvent {
    data class OnTitleChanged(val input: String): CheckboxNoteEvent
    data class OnItemNameChanged(val itemId: String, val input: String): CheckboxNoteEvent
    data class OnItemChecked(val itemId: String): CheckboxNoteEvent
    object OnNewItemClicked: CheckboxNoteEvent
}