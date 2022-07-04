package com.example.smartparking.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartparking.databinding.FragmentProfileBinding
import com.example.smartparking.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding
        firebaseAuth = FirebaseAuth.getInstance()
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding!!.btnBack.setOnClickListener {

            requireActivity().onBackPressed()
        }
        binding!!.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireActivity(),LoginActivity::class.java))
            requireActivity().finish()
        }

        return binding!!.getRoot()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}