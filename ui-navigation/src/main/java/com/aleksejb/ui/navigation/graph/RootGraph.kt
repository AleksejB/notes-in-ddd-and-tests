package com.aleksejb.ui.navigation.graph

import com.aleksejb.ui.navigation.NavGraph
import kotlinx.parcelize.Parcelize

sealed interface RootGraph: NavGraph {

    @Parcelize
    object NotesGraph: RootGraph
}