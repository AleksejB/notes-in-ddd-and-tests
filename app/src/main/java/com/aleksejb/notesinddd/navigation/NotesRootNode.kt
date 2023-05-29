package com.aleksejb.notesinddd.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.ui.navigation.graph.NotesGraph
import com.aleksejb.ui.note.node.NoteNode
import com.aleksejb.ui.notes.node.NotesNode
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack

class NotesRootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NotesGraph> = BackStack(
        initialElement = NotesGraph.Notes,
        savedStateMap = buildContext.savedStateMap
    )
): ParentNode<NotesGraph>(buildContext = buildContext, navModel = backStack) {

    override fun resolve(navTarget: NotesGraph, buildContext: BuildContext): Node {
        return when (navTarget) {
            is NotesGraph.Notes -> NotesNode(buildContext = buildContext)
            is NotesGraph.Note -> NoteNode(
                buildContext = buildContext,
                noteBasicInfo = NoteBasicInfo(0, NoteType.TEXT)
            )
        }
    }
    
    @Composable
    override fun View(modifier: Modifier) {
        Children(navModel = backStack)
    }
}