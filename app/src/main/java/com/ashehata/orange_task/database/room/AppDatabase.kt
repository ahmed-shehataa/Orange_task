package com.ashehata.orange_task.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ashehata.orange_task.modules.news.data.local.dao.NewsDao
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel

@Database(entities = [NewsDataModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
