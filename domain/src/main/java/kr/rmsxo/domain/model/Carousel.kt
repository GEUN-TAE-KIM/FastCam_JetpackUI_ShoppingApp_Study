package kr.rmsxo.domain.model

data class Carousel(
    val carouseId: String,
    val title: String,
    val productList: List<Product>,
    override val type: ModelType = ModelType.CAROUSEL,
): BaseModel()
