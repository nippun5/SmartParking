package com.example.smartparking.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartparking.R
import com.example.smartparking.databinding.BottomsheetBasicBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomBasic: BottomSheetDialogFragment() {
    lateinit var binding: BottomsheetBasicBinding
    fun BlankFragment() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetBasicBinding.inflate(inflater,container,false)
        return binding.root
    }
}