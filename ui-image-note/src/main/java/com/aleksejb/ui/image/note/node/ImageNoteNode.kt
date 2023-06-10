package com.aleksejb.ui.image.note.node

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.aleksejb.core.ui.appyx.ViewModelOwnerPlugin
import com.aleksejb.ui.image.note.ImageNoteScreen
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class ImageNoteNode(
    buildContext: BuildContext,
    private val noteId: Int?,
    private val viewModelOwnerPlugin: ViewModelOwnerPlugin = ViewModelOwnerPlugin()
): Node(
    buildContext = buildContext,
    plugins = listOf(viewModelOwnerPlugin)
) {

    @Composable
    override fun View(modifier: Modifier) {
        CompositionLocalProvider(
            LocalViewModelStoreOwner provides viewModelOwnerPlugin
        ) {
            ImageNoteScreen(noteId = noteId)
        }
    }
}