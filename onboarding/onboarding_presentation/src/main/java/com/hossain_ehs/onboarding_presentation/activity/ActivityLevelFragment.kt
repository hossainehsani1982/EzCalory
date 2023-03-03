package com.hossain_ehs.onboarding_presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityLevelFragment : Fragment(R.layout.fragment_activity) {
    private lateinit var binding: FragmentActivityBinding
    private val activityLevelViewModel: ActivityLevelViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)

        subscribeToObservers()

        binding.apply {
            activityLevelPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    ActivityLevelScreen()
                }
            }
        }
    }

    private fun subscribeToObservers() {
        activityLevelViewModel.activityLevelChannel
            .asLiveData().observe(viewLifecycleOwner) { events ->
                when (events) {
                    UiEvents.NavigateUp -> {
                        val action = ActivityLevelFragmentDirections
                            .actionActivityLevelFragmentToGoalFragment()
                        findNavController().navigate(action)
                    }
                    is UiEvents.ShowSnackBar -> Unit
                }
            }
    }
}