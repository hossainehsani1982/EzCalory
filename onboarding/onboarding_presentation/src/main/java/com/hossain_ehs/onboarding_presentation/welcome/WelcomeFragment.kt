package com.hossain_ehs.onboarding_presentation.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.hossain_ehs.onboarding_presentation.R
import com.hossain_ehs.onboarding_presentation.databinding.FragmentWelcomeBinding
import com.plcoding.calorytracker.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    lateinit var binding : FragmentWelcomeBinding
    companion object  {
        var navi = MutableLiveData<Boolean>()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        binding.apply {
            homePageComposeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent{
                    MyAppTheme{
                        WelcomeScreen()
                    }

                }
            }
        }

        navi.observe(viewLifecycleOwner){
            if(it){
                navigate()
            }
        }
    }


    private fun navigate(){
        val action = WelcomeFragmentDirections.actionWelcomeFragment2ToGenderFragment()
        findNavController().navigate(action)
    }
}