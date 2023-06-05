package com.aleksejb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteBasicInfo(
    val id: Int,
    val noteType: NoteType
): Parcelable
