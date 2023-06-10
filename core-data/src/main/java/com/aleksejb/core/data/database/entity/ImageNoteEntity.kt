package com.aleksejb.core.data.database.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleksejb.core.data.util.decodeStringToBitmap
import com.aleksejb.core.data.util.encodeBitmapToString
import com.aleksejb.core.domain.model.ImageNote
import java.io.ByteArrayOutputStream

@Entity(tableName = "image_note")
data class ImageNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val images: List<String>
)

fun ImageNoteEntity.toImageNote() = ImageNote(
    id = id,
    title = title,
    images = images.map { encodedString -> decodeStringToBitmap(encodedString) }
)

fun ImageNote.toImageNoteEntity() = ImageNoteEntity(
    id = id,
    title = title,
    images = images.map { bitmap -> encodeBitmapToString(bitmap) }
)