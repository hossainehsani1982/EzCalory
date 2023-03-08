package com.hossain_ehs.tracker_presentation.tracker_search_food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.hossain_ehs.tracker_presentation.R
import com.hossain_ehs.tracker_presentation.databinding.FragmentSearchFoodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFoodFragment : Fragment(R.layout.fragment_search_food) {
    private lateinit var binding : FragmentSearchFoodBinding
    val searchViewModel : SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchFoodBinding.bind(view)

        binding.apply {
            searchFoodPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    SearchScreen(
                        mealName = searchViewModel.mealName,
                        dayOfMonth = searchViewModel.day,
                        month = searchViewModel.month,
                        year = searchViewModel.year)
                }
            }
        }
    }

}