package com.aleksejb.ui.navigation.graph

import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.ui.navigation.NavGraph
import kotlinx.parcelize.Parcelize

sealed interface NotesGraph: NavGraph {

    @Parcelize
    object Notes: NotesGraph

    @Parcelize
    data class TextNote(val noteId: Int?): NotesGraph

    @Parcelize
    data class CheckboxNote(val noteId: Int?): NotesGraph

    @Parcelize
    data class ImageNote(val noteId: Int?): NotesGraph
}