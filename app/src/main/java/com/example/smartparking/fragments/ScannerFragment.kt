package com.example.smartparking.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.smartparking.R
import com.google.zxing.integration.android.IntentIntegrator;

class ScannerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) {
                ScanButton(view)

        }
            else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.CAMERA), 1)

        }


        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    fun ScanButton(view: View?) {
        val intentIntegrator = IntentIntegrator(requireActivity())
        intentIntegrator.initiateScan()
    }
}