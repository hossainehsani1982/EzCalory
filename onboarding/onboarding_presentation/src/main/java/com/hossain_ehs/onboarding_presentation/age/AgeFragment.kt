package com.hossain_ehs.onboarding_presentation.age

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
import com.hossain_ehs.onboarding_presentation.databinding.FragmentAgeBinding
import com.plcoding.calorytracker.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgeFragment : Fragment(R.layout.fragment_age) {
    private lateinit var binding: FragmentAgeBinding
    private val ageViewModel :  AgeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAgeBinding.bind(view)

        subscribeToObservers()

        binding.apply {
            agePageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent{
                    MyAppTheme {
                        AgeScreen()
                    }
                }
            }
        }
    }

    private fun subscribeToObservers(){
        ageViewModel.ageChannel.asLiveData().observe(viewLifecycleOwner){events->
            when(events){
                UiEvents.NavigateUp -> {
                val action = AgeFragmentDirections.actionAgeFragmentToHeightFragment()
                    findNavController().navigate(action)
                }
                is UiEvents.ShowSnackBar -> {
                   Snackbar.make(requireView()
                       ,events.message.asString(requireContext()),
                       Snackbar.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }
    }

}