package com.example.vesta.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vesta.activities.condo.CreateCondoActivity
import com.example.vesta.data.user.addUserToDatabase
import com.example.vesta.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        auth = Firebase.auth
        db = Firebase.firestore

        //Signup listener
        binding.signupCreateAccountBtn.setOnClickListener {
            signupManager()
        }
    }

    private fun signupManager() {
        val name = binding.signupNameEt.text.toString().trim { it <= ' '}
        val email = binding.signupLoginEt.text.toString().trim { it <= ' ' }.lowercase()
        val password = binding.signupPasswordEt.text.toString().trim { it <= ' ' }

        if (name.isEmpty()) {
            Toast.makeText(this,
                "Por favor, insira seu nome!", Toast.LENGTH_SHORT
            )
                .show()
            return
        }

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

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(db, name, email, "manager") {
                        Toast.makeText(this,
                            "Usuário criado com sucesso.\nRedirecionando...", Toast.LENGTH_SHORT
                        )
                            .show()

                        intent = Intent(this, CreateCondoActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                } else {
                    Toast.makeText(this,
                    "Falha no cadastro, tente novamente!", Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }


    }


}