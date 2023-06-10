package com.aleksejb.ui.image.note

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aleksejb.core.data.util.getBitmapFromUri
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.aleksejb.core.ui.R

@Composable
fun ImageNoteScreen(
    noteId: Int?,
    viewModel: ImageNoteViewModel = koinViewModel(parameters = { parametersOf(noteId ) })
) {
    val state by viewModel.state.collectAsState()

    DisposableEffect(key1 = Unit) { onDispose { viewModel.onDispose() } }

    ImageNoteScreenContent(state = state, eventHandler = viewModel::postEvent)
}

@Composable
private fun ImageNoteScreenContent(
    state: ImageNoteState,
    eventHandler: (ImageNoteEvent) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val itemSize = screenWidth / 3
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { eventHandler(ImageNoteEvent.OnNewImageSelected(context.getBitmapFromUri(uri))) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.medium_100))
    ) {
        TitleTextField(state, eventHandler)

        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.medium_100)),
            columns = GridCells.Fixed(3)
        ) {
            items(state.images) { image ->
                Image(
                    modifier = Modifier.size(itemSize.dp),
                    bitmap = image.asImageBitmap(),
                    contentDescription = null
                )
            }

            item {
                NewImageClickableSurface(itemSize) {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            }
        }
    }
}

@Composable
private fun NewImageClickableSurface(itemSize: Int, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .size(itemSize.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_100)),
        color = Color.Gray
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null
        )
    }
}

@Composable
private fun TitleTextField(
    state: ImageNoteState,
    eventHandler: (ImageNoteEvent) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.title,
        onValueChange = { eventHandler(ImageNoteEvent.OnTitleChanged(it)) }
    )
}