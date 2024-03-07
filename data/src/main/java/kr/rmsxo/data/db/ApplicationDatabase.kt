package kr.rmsxo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.rmsxo.data.db.dao.BasketDao
import kr.rmsxo.data.db.dao.LikeDao
import kr.rmsxo.data.db.dao.PurchaseDao
import kr.rmsxo.data.db.dao.SearchDao
import kr.rmsxo.data.db.entity.BasketProductEntity
import kr.rmsxo.data.db.entity.LikeProductEntity
import kr.rmsxo.data.db.entity.PurchaseProductEntity
import kr.rmsxo.data.db.entity.SearchKeywordEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class,
        SearchKeywordEntity::class,
      //  PurchaseHistoryEntity::class,
    ],
    version = 3
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun purchaseDao() : PurchaseDao

    abstract fun likeDao() : LikeDao

    abstract fun basketDao() : BasketDao

    abstract fun searchDao() : SearchDao

   // abstract fun purchaseHistoryDao() : PurchaseHistoryDao
}