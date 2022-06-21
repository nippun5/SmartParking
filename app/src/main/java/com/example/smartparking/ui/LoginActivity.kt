package com.example.smartparking.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        Log.d("Tag", "firebase " + firebaseAuth)
        binding.btnLogin.setOnClickListener {

            startActivity(Intent(this, DashboardActivity::class.java))
//            val email = binding.email.text.toString().trim()
//            val pass = binding.password.text.toString().trim()
//
//            Log.i("TAG", "onCreate: " + email)
//            Log.i("TAG", "onCreate: " + pass)
//            firebaseAuth.signInWithEmailAndPassword(email, pass)
//                .addOnCompleteListener(this) { task ->
//
//                    if (task.isSuccessful) {
////                        val user = firebaseAuth.currentUser
//                        Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this, DashboardActivity::class.java))
//                        finish()
//                    } else {
//                        Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
//                    }
//                }

        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}