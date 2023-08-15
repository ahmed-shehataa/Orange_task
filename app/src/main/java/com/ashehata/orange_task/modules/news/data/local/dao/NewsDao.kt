package com.ashehata.orange_task.modules.news.data.local.dao

import androidx.room.*
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE title LIKE '%' || :keyword || '%' ORDER BY publishedAt DESC LIMIT :perPage OFFSET (:page - 1) * :perPage")
    fun getAll(page: Int, perPage: Int, keyword: String): List<NewsDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsList: List<NewsDataModel>)

    @Query("DELETE FROM news WHERE url = :url")
    suspend fun delete(url: String)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

}