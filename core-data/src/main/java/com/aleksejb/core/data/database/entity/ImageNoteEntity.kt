package com.aleksejb.core.data.database.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    images = images.map { encodedString ->
        val byteArray = Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
)

fun ImageNote.toImageNoteEntity() = ImageNoteEntity(
    id = id,
    title = title,
    images = images.map { bitmap ->
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
)