package com.hossain_ehs.tracker_presentation.tracker_search_food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.tracker_presentation.R
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.tracker_presentation.databinding.FragmentSearchFoodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFoodFragment : Fragment(R.layout.fragment_search_food) {
    private lateinit var binding: FragmentSearchFoodBinding
    val searchViewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchFoodBinding.bind(view)
        subscribeToObservers()
        binding.apply {
            searchFoodPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {

                    SearchScreen(
                        mealName = searchViewModel.mealName,
                        dayOfMonth = searchViewModel.day,
                        month = searchViewModel.month,
                        year = searchViewModel.year
                    )
                }
            }
        }
    }

    private fun subscribeToObservers() {
        searchViewModel.searchUiEvens.asLiveData().observe(viewLifecycleOwner) { events ->
            when (events) {
                is UiEvents.NavigateToSearchFood -> TODO()
                UiEvents.NavigateUp -> {
                    val action = SearchFoodFragmentDirections
                        .actionSearchFoodFragmentToTrackerOverViewFragment()
                    findNavController().navigate(action)

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