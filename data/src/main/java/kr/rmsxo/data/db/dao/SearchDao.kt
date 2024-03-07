package kr.rmsxo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.rmsxo.data.db.entity.SearchKeywordEntity

@Dao
interface SearchDao {

    @Query("SELECT * FROM search")
    fun getAll() : Flow<List<SearchKeywordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SearchKeywordEntity)
}