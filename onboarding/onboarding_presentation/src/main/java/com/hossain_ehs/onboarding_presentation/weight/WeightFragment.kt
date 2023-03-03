package com.hossain_ehs.onboarding_presentation.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentWeightBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeightFragment : Fragment(R.layout.fragment_weight) {
    private lateinit var binding : FragmentWeightBinding
    private val viewModel : WeightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeightBinding.bind(view)

        subscribeToObserves()

        binding.apply {
            weightPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    WeightScreen()
                }
            }
        }
    }

    private fun subscribeToObserves(){
        viewModel.weightChannel.asLiveData().observe(viewLifecycleOwner){events->
            when(events){
                UiEvents.NavigateUp -> {

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