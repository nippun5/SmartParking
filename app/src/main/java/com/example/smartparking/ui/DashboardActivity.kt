package com.example.smartparking.ui


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.smartparking.R
import com.example.smartparking.databinding.ActivityDashboardBinding


class DashboardActivity : FragmentActivity() {
    lateinit var binding: ActivityDashboardBinding
    // AIzaSyAs0419ev67oh8rBVclJ4g8WZBGtAIx15A
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}