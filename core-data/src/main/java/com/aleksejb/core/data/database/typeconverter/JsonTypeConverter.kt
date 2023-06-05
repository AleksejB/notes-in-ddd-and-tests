package com.aleksejb.core.data.database.typeconverter

interface JsonTypeConverter<T> {
    fun from(value: T): String
    fun to(value: String): T
}