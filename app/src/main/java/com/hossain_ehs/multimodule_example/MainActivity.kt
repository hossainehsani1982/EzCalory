package com.hossain_ehs.multimodule_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.hossain_ehs.core_ui.NavigationModule
import com.hossain_ehs.core_ui.Navigator
import com.hossain_ehs.core_ui.ToModuleNavigatable
import com.hossain_ehs.multimodule_example.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToModuleNavigatable {
    private val navigator: Navigator = Navigator()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun navigateToModule(flow: NavigationModule) {
        navigator.navigateToFlow(flow)
    }
}