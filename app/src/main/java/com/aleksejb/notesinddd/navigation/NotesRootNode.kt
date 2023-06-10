package com.aleksejb.notesinddd.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.core.ui.appyx.ViewModelOwnerPlugin
import com.aleksejb.ui.checkbox.note.node.CheckboxNoteNode
import com.aleksejb.ui.image.note.node.ImageNoteNode
import com.aleksejb.ui.navigation.Navigator
import com.aleksejb.ui.navigation.graph.NotesGraph
import com.aleksejb.ui.notes.node.NotesNode
import com.aleksejb.ui.text.note.node.TextNoteNode
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.newRoot
import com.bumble.appyx.navmodel.backstack.operation.push

class NotesRootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NotesGraph> = BackStack(
        initialElement = NotesGraph.Notes,
        savedStateMap = buildContext.savedStateMap
    ),
    viewModelOwnerPlugin: ViewModelOwnerPlugin = ViewModelOwnerPlugin(),
): ParentNode<NotesGraph>(
    buildContext = buildContext,
    navModel = backStack,
    plugins = listOf(viewModelOwnerPlugin)
), Navigator<NotesGraph> {

    override fun resolve(navTarget: NotesGraph, buildContext: BuildContext): Node {
        return when (navTarget) {
            is NotesGraph.Notes -> NotesNode(
                buildContext = buildContext,
                navigator = this
            )
            is NotesGraph.CheckboxNote -> CheckboxNoteNode(
                buildContext = buildContext,
                noteId = navTarget.noteId
            )
            is NotesGraph.ImageNote -> ImageNoteNode(
                buildContext = buildContext,
                noteId = navTarget.noteId
            )
            is NotesGraph.TextNote -> TextNoteNode(
                buildContext = buildContext,
                noteId = navTarget.noteId
            )
        }
    }

    override fun navigate(destination: NotesGraph): Boolean {
        when (destination) {
            is NotesGraph.CheckboxNote -> backStack.push(destination)
            is NotesGraph.ImageNote -> backStack.push(destination)
            is NotesGraph.TextNote -> backStack.push(destination)
            is NotesGraph.Notes -> backStack.newRoot(destination)
        }

        return true
    }
    
    @Composable
    override fun View(modifier: Modifier) {
        Children(navModel = backStack)
    }
}