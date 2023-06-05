package com.aleksejb.core.domain.model

@kotlinx.serialization.Serializable
data class CheckboxItem(
    val itemName: String,
    val isComplete: Boolean
)