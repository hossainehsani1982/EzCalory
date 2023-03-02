package com.hossain_ehs.onboarding_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hossain_ehs.onboarding_presentation.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding : FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.apply {

        }
    }
}