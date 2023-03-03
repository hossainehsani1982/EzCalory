package com.hossain_ehs.onboarding_presentation.gender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentGenderBinding
import com.hossain_ehs.onboarding_presentation.welcome.WelcomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderFragment : Fragment(R.layout.fragment_gender) {
    private lateinit var binding : FragmentGenderBinding
    private val genderViewModel : GenderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGenderBinding.bind(view)
        subscribeToObservers()
        binding.apply {
            genderPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    GenderScreen()
                }
            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                WelcomeFragment.navi.value = false
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    private fun subscribeToObservers(){
        genderViewModel.genderChannel.asLiveData().observe(viewLifecycleOwner){ events->
            when(events){
                UiEvents.NavigateUp -> {
                    val action = GenderFragmentDirections.actionGenderFragmentToAgeFragment()
                    findNavController().navigate(action)
                }
                is UiEvents.ShowSnackBar -> TODO()
            }

        }
    }
}