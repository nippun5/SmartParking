package com.example.smartparking.bottomSheet

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R
import com.example.smartparking.base.User
import com.example.smartparking.databinding.BottomsheetBasicBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomBasic : BottomSheetDialogFragment() {
    lateinit var binding: BottomsheetBasicBinding
    lateinit var usersList: ArrayList<User>
    private lateinit var recyclerView: RecyclerView
    fun BlankFragment() {

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

        usersList = ArrayList()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetBasicBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {

            onDismiss(dialog!!)
            Log.i("TAG", "onViewCreated: $dialog")
        }
        binding.btnDetails.setOnClickListener {


        }
    }
}