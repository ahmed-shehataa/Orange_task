package com.ashehata.orange_task.database.room

import androidx.room.TypeConverter
import com.ashehata.orange_task.modules.news.data.model.SourceDataModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Converters {

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    @TypeConverter
    fun fromSourceDataModel(source: SourceDataModel?): String? {
        return if (source == null) null else moshi.adapter(SourceDataModel::class.java)
            .toJson(source)
    }

    @TypeConverter
    fun toSourceDataModel(sourceJson: String?): SourceDataModel? {
        return if (sourceJson == null) null else moshi.adapter(SourceDataModel::class.java)
            .fromJson(sourceJson)
    }
}