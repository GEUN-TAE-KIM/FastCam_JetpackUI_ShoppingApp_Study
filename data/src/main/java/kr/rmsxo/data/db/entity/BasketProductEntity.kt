package kr.rmsxo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kr.rmsxo.data.db.converter.BasketConverter
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Price
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Shop

@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isLike: Boolean,
    val isFreeShipping: Boolean,
)

fun BasketProductEntity.toDomainModel() : Product {
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
