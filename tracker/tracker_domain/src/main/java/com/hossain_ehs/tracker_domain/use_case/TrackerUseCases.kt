package com.hossain_ehs.tracker_domain.use_case

data class TrackerUseCases(
    val calculateMealNutrients: CalculateMealNutrients,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodsForDate: GetFoodsForDate,
    val searchFoodUseCase: SearchFoodUseCase,
    val trackFoodUseCase: TrackFoodUseCase
)
