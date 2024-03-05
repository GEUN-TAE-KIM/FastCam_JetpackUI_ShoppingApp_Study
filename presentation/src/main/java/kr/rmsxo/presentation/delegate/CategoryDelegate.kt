package kr.rmsxo.presentation.delegate

import androidx.navigation.NavHostController
import kr.rmsxo.domain.model.Category

interface CategoryDelegate {

    fun openCategory(navHostController: NavHostController, category: Category)
}