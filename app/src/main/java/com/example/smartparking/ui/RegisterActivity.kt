package com.example.smartparking.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
        binding.btnSignup.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
//            if (TextUtils.isEmpty(email)) {
//                binding.etEmail.error = "The field is mandatory."
//                return@setOnClickListener
//            }
//            if (TextUtils.isEmpty(pass)) {
//                binding.etPassword.error = "The field is mandatory."
//                return@setOnClickListener
//            }
//            if (TextUtils.isEmpty(binding.etFirstName.toString().trim()) || TextUtils.isEmpty(binding.etLastName.toString().trim())) {
//                binding.etFirstName.error = "The field is mandatory."
//                binding.etLastName.error = "The field is mandatory."
//                return@setOnClickListener
//            }


            //register user
            firebaseAuth.createUserWithEmailAndPassword(
                email, pass
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "User Created", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(applicationContext, DashboardActivity::class.java))
                        finish()
                        binding.progressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error!" + task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.visibility = View.GONE
                    }
                }

            binding.txtIntentToLogin.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}