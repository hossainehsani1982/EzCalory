package com.hossain_ehs.tracker_presentation.tracker_overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.tracker_presentation.R
import com.hossain_ehs.tracker_presentation.databinding.FragmentTrackerOverViewBinding
import com.plcoding.calorytracker.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackerOverViewFragment : Fragment(R.layout.fragment_tracker_over_view) {
    private lateinit var binding : FragmentTrackerOverViewBinding
    private val trackerOverViewViewModel : TrackerOverViewViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackerOverViewBinding.bind(view)
        subscribeToObservers()
        binding.apply {
            trackerOverviewPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MyAppTheme {
                        TrackerOverViewScreen()
                    }
                }
            }
        }

    }

    private fun subscribeToObservers(){
        trackerOverViewViewModel.trackerOverViewChannel
            .asLiveData()
            .observe(viewLifecycleOwner){ events->
                when(events){
                    UiEvents.NavigateUp -> TODO()
                    is UiEvents.ShowSnackBar -> TODO()
                    is UiEvents.NavigateToSearchFood -> {
                        val action = TrackerOverViewFragmentDirections
                            .actionTrackerOverViewFragmentToSearchFoodFragment(
                                mealType = events.mealType,
                                day = events.day,
                                month = events.month ,
                                year = events.year
                            )
                        findNavController().navigate(action)
                    }
                }

        }
    }
}