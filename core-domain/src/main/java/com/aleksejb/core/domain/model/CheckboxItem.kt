package com.aleksejb.core.domain.model

@kotlinx.serialization.Serializable
data class CheckboxItem(
    val id: String,
    val itemName: String,
    val isComplete: Boolean
)