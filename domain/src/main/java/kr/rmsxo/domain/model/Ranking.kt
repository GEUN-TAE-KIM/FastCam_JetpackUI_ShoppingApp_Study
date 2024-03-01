package kr.rmsxo.domain.model

data class Ranking(
    val ranking: String,
    val title: String,
    val productList: List<Product>,
    override val type: ModelType = ModelType.RANKING,
) : BaseModel()
