package com.hossain_ehs.onboarding_domain.di

import com.hossain_ehs.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideValidateNutrientUseCase() = ValidateNutrientsUseCase()
}