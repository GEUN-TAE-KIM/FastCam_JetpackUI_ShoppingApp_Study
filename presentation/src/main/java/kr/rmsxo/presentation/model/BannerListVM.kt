package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.presentation.delegate.BannerDelegate

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate): PresentationVM<BannerList>(model) {

    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
    }
}