package kr.rmsxo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kr.rmsxo.data.db.converter.LikeConverter
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Price
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Shop

@Entity(tableName = "like")
@TypeConverters(LikeConverter::class)
data class LikeProductEntity (
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean,
    val isLike: Boolean
)

fun LikeProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping,
        isLike = isLike
    )
}
