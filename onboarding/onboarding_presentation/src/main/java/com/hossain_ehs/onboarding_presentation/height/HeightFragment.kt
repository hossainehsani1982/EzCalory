package com.hossain_ehs.onboarding_presentation.height

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentHeightBinding
import com.plcoding.calorytracker.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeightFragment : Fragment(R.layout.fragment_height) {
    private lateinit var binding : FragmentHeightBinding
    private val viewModel : HeightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeightBinding.bind(view)
        subscribeToObserves()
        binding.apply {
            heightPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MyAppTheme {
                        HeightScreen()
                    }
                }
            }
        }
    }

    private fun subscribeToObserves(){
        viewModel.heightChannel.asLiveData().observe(viewLifecycleOwner){events->
            when(events){
                UiEvents.NavigateUp -> {
                    val action = HeightFragmentDirections.actionHeightFragmentToWeightFragment()
                    findNavController().navigate(action)
                }
                is UiEvents.ShowSnackBar -> {
                    Snackbar.make(
                        requireView(),
                        events.message.asString(requireContext()),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> Unit
            }
        }
    }
}