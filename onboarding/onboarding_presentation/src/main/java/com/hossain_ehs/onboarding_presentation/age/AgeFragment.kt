package com.hossain_ehs.onboarding_presentation.age

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentAgeBinding
import com.hossain_ehs.onboarding_presentation.welcome.WelcomeFragment

class AgeFragment : Fragment(R.layout.fragment_age) {
    lateinit var binding: FragmentAgeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAgeBinding.bind(view)



        binding.apply {
            agePageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent{
                    AgeScreen()
                }
            }
        }

    }

}