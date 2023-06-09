package com.hossain_ehs.onboarding_presentation.goal

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.core.util.UiEvents
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentGoalBinding
import com.plcoding.calorytracker.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GoalFragment : Fragment(R.layout.fragment_goal) {
    private lateinit var binding: FragmentGoalBinding
    private val goalViewModel : GoalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGoalBinding.bind(view)

        subscribeToObserves()

        binding.apply {
            goalPageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MyAppTheme {
                        GoalScreen()
                    }
                }
            }
        }
    }

    private fun subscribeToObserves(){
        goalViewModel.goalChannel.asLiveData().observe(viewLifecycleOwner){events->
            when(events){
                UiEvents.NavigateUp -> {
                val action = GoalFragmentDirections.actionGoalFragmentToNutrientGoalFragment()
                findNavController().navigate(action)
                }
                is UiEvents.ShowSnackBar -> Unit
                else -> Unit
            }
        }
    }
}