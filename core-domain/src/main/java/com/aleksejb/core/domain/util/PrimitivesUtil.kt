package com.aleksejb.core.domain.util

import com.aleksejb.core.domain.util.Constants.NON_EXISTENT_NOTE_ID
import java.util.*

fun String.capitalise() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

fun String.capitalizeFullCapsString() = this.lowercase().capitalize().replace("_", " ")

fun Int?.noteExists(): Boolean = this != null

fun Int?.noteDoesNotExists(): Boolean = this == null