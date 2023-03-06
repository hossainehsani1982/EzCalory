package com.hossain_ehs.tracker_domain.di

import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.tracker_domain.repository.TrackerRepository
import com.hossain_ehs.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped



@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {
    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) : TrackerUseCases{
        return TrackerUseCases(
            calculateMealNutrients = CalculateMealNutrients(preferences) ,
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            searchFoodUseCase = SearchFoodUseCase(repository),
            trackFoodUseCase = TrackFoodUseCase(repository)
        )
    }
}