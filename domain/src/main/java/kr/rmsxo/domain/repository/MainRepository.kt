package kr.rmsxo.domain.repository

import kr.rmsxo.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>

}