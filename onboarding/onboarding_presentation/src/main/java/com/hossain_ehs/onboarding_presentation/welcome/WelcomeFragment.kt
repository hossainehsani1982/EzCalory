package com.hossain_ehs.onboarding_presentation.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.material.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    lateinit var binding : FragmentWelcomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        binding.apply {
            homePageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent{
                    WelcomeScreen()
                }

            }
        }
    }
}