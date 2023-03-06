package com.hossain_ehs.onboarding_presentation.nutrient_goal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.core_ui.*
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentNutrientGoalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NutrientGoalFragment : Fragment(R.layout.fragment_nutrient_goal) {

    private lateinit var binding: FragmentNutrientGoalBinding
    private val nutrientGoalViewModel: NutrientGoalViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNutrientGoalBinding.bind(view)

        subscribeToObservers()

        binding.apply {
            nutrientGoalPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    NutrientGoalScreen()
                }
            }
        }
    }

    private fun subscribeToObservers() {
        nutrientGoalViewModel.nutrientGoalChannel.asLiveData()
            .observe(viewLifecycleOwner) { events ->
                when (events) {
                    UiEvents.NavigateUp -> {
                        findNavController()
                            .navigate(NavigationMainDirections
                                .actionGlobalTrackerNav())

                    }
                    is UiEvents.ShowSnackBar -> {
                        Snackbar.make(
                            requireView(),
                            events.message.asString(requireContext()),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }
}