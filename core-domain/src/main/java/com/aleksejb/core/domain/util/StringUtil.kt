package com.aleksejb.core.domain.util

import java.util.*

fun String.capitalise() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

fun String.capitalizeFullCapsString() = this.lowercase().capitalize().replace("_", " ")