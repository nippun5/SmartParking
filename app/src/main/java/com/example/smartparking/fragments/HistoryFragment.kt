package com.example.smartparking.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartparking.adapter.RecyclerAdapter
import com.example.smartparking.base.User
import com.example.smartparking.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding:FragmentHistoryBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        binding.rvHistoryItem.layoutManager = LinearLayoutManager(
            activity
        )

        val list = arrayListOf(
            User("dsdsd"),
            User("dsd,sd"),
            User("dsdsd"),
            User("dsdsd"),
            User("dsdsd"),
            User("dsdsd"),
            User("dsdsd"),
            User("dsdsd")
        )
        adapter =
            RecyclerAdapter(requireContext(), list)

        binding.rvHistoryItem.adapter = adapter
        // Inflate the layout for this fragment
        return binding.root
    }
}