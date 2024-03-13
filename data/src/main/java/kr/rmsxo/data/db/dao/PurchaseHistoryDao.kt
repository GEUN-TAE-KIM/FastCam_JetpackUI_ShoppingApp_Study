package kr.rmsxo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.rmsxo.data.db.entity.PurchaseHistoryEntity

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM history")
    fun getAll() : Flow<List<PurchaseHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : PurchaseHistoryEntity)

    @Query("SELECT * FROM history WHERE id=:id")
    suspend fun get(id: String): PurchaseHistoryEntity?

    @Query("DELETE FROM history WHERE id=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM history")
    suspend fun deleteAll()

}