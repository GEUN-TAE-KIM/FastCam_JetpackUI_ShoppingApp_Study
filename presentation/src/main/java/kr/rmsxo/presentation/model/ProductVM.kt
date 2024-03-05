package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.Product
import kr.rmsxo.presentation.delegate.ProductDelegate

class ProductVM(model: Product, productDelegate: ProductDelegate) : PresentationVM<Product>(model),
    ProductDelegate by productDelegate {
}