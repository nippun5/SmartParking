package com.example.smartparking


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.smartparking.databinding.ActivityDashboardBinding
import com.example.smartparking.fragments.HomeFragment
import com.google.android.gms.maps.MapFragment




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