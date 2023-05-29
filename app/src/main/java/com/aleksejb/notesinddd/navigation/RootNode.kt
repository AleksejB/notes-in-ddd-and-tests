package com.aleksejb.notesinddd.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.ui.navigation.graph.RootGraph
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack

class RootNode(
    buildContext: BuildContext,
    private val backstack: BackStack<RootGraph> = BackStack(
        initialElement = RootGraph.NotesGraph,
        savedStateMap = buildContext.savedStateMap
    )
): ParentNode<RootGraph>(buildContext = buildContext, navModel = backstack) {

    override fun resolve(navTarget: RootGraph, buildContext: BuildContext): Node {
        return when (navTarget) {
            is RootGraph.NotesGraph -> {
                Log.d("TAAAG", "in RootNode, navigating to NotesRootNode")
                NotesRootNode(buildContext)
            }
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(navModel = backstack)
    }
}