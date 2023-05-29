package com.aleksejb.ui.navigation.graph

import com.aleksejb.ui.navigation.NavGraph
import kotlinx.parcelize.Parcelize

sealed interface NotesGraph: NavGraph {

    @Parcelize
    object Notes: NotesGraph

    @Parcelize
    data class Note(val noteId: Int): NotesGraph
}