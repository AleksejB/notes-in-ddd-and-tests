package com.aleksejb.ui.notes.node

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.ui.notes.NotesScreen
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class NotesNode(
    buildContext: BuildContext
): Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Log.d("TAAAG", "in NotesNode, opening NotesScreen") //not getting to here
        NotesScreen()
    }
}