package com.aleksejb.core.domain.model

interface Note {
    object ImageNote: Note
    object TextNote: Note
    object CheckboxNote: Note
}