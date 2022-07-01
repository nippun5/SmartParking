package com.example.smartparking.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.base.CustomToast
import com.example.smartparking.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser





class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var customToast: CustomToast
    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = firebaseAuth.getCurrentUser()



         if (currentUser !=null) {
            // User logged in
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
        customToast = CustomToast(this)
        firebaseAuth = FirebaseAuth.getInstance()
        Log.d("Tag", "firebase " + firebaseAuth)
        binding.email.setText("asd@gmail.com")
        binding.password.setText("123456")




        binding.btnLogin.setOnClickListener {

//            startActivity(Intent(this, DashboardActivity::class.java))
            val email = binding.email.text.toString().trim()
            val pass = binding.password.text.toString().trim()
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(applicationContext, "Emplty Fields", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("TAG", "onCreate: " + email)
                Log.i("TAG", "onCreate: " + pass)
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            customToast.showToast()
                            startActivity(Intent(this, DashboardActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


}