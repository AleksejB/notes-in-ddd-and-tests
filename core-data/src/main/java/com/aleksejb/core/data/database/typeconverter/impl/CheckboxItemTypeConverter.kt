package com.aleksejb.core.data.database.typeconverter.impl

import androidx.room.TypeConverter
import com.aleksejb.core.data.database.typeconverter.JsonTypeConverter
import com.aleksejb.core.domain.model.CheckboxItem
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CheckboxItemTypeConverter: JsonTypeConverter<List<CheckboxItem>> {

//    @TypeConverter
//    override fun from(value: CheckboxItem): String {
//        return Json.encodeToString(value)
//    }
//
//    @TypeConverter
//    override fun to(value: String): CheckboxItem {
//        return Json.decodeFromString(CheckboxItem.serializer(), value)
//    }

    @TypeConverter
    override fun from(value: List<CheckboxItem>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    override fun to(value: String): List<CheckboxItem> {
        return Json.decodeFromString(ListSerializer(CheckboxItem.serializer()), value)
    }
}
