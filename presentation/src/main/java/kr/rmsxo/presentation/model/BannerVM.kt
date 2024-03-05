package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.Banner
import kr.rmsxo.presentation.delegate.BannerDelegate

class BannerVM(model: Banner, bannerDelegate: BannerDelegate): PresentationVM<Banner>(model), BannerDelegate by bannerDelegate {

}