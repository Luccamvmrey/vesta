package com.example.vesta.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vesta.CondoActivity
import com.example.vesta.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        //Checks if user is logged in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            getCondoPage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        //Login listener
        binding.loginLoginBtn.setOnClickListener {
            loginUser(binding)
        }

        //Create account listener
        binding.loginNewAccountTv.setOnClickListener {
            getNewAccountPage()
        }
    }

    private fun loginUser(binding: ActivityLoginBinding) {
        val email = binding.loginLoginEt.text.toString().trim { it <= ' '}
        val password = binding.loginPasswordEt.text.toString().trim { it <= ' '}

        if (email.isEmpty()) {
            Toast.makeText(this,
            "Por favor, insira seu e-mail!", Toast.LENGTH_SHORT
            )
                .show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this,
                "Por favor, insira sua senha!", Toast.LENGTH_SHORT
            )
                .show()
            return
        }
    }

    private fun getCondoPage() {
        intent = Intent(this, CondoActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getNewAccountPage() {
        intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
}
