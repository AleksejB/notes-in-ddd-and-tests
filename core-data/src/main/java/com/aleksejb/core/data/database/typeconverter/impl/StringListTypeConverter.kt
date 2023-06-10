package com.aleksejb.core.data.database.typeconverter.impl

import androidx.room.TypeConverter
import com.aleksejb.core.data.database.typeconverter.JsonTypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringListTypeConverter: JsonTypeConverter<List<String>> {

    @TypeConverter
    override fun from(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    override fun to(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }

}