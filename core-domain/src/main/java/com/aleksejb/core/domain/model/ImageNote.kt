package com.aleksejb.core.domain.model

data class ImageNote(
    val title: String,
    val images: List<String> //TODO: change to image of some sort
): Note