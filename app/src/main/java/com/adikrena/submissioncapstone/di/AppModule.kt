package com.adikrena.submissioncapstone.di

import com.adikrena.submissioncapstone.core.domain.usecase.RecipeInteractor
import com.adikrena.submissioncapstone.core.domain.usecase.RecipeUseCase
import com.adikrena.submissioncapstone.detail.DetailRecipeViewModel
import com.adikrena.submissioncapstone.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipeUseCase> { RecipeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailRecipeViewModel(get()) }
}