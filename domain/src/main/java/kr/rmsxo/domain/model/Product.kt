package kr.rmsxo.domain.model

data class Product(
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
