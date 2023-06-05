package com.aleksejb.ui.text.note

sealed interface TextNoteEvent {
    data class OnTitleChanged(val input: String): TextNoteEvent
    data class OnTextChanged(val input: String): TextNoteEvent
}