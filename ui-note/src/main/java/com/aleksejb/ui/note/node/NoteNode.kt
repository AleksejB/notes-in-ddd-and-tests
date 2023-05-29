package com.aleksejb.ui.note.node

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.core.domain.model.Note
import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.ui.note.NoteScreen
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class NoteNode(
    buildContext: BuildContext,
    private val noteBasicInfo: NoteBasicInfo
): Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) = NoteScreen(noteBasicInfo)
}