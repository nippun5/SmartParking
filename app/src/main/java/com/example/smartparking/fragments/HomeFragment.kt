package com.example.smartparking.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartparking.databinding.FragmentHomeBinding
import com.example.smartparking.retrofit.ApiRequest


class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val apiRequest = ApiRequest()
        apiRequest.getParkingLots()

        binding.rvParkingLots.layoutManager=GridLayoutManager(activity, 2)

//        binding.rvParkingLots.adapter = adapter

        return (binding.root)
    }
    }
