package com.example.vesta.activities.condo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vesta.data.condo.addNewCondoToDatabase
import com.example.vesta.databinding.ActivityCreateCondoBinding
import com.example.vesta.domain.populateCondoWithTowersAndStories
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateCondoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCondoBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCondoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        auth = Firebase.auth
        db = Firebase.firestore

        //Create condo
        binding.newCondoCreateCondoBtn.setOnClickListener {
            createCondo()
        }
    }

    private fun createCondo() {
        val numberOfTowers = binding.newCondoNumberTowersEt.text.toString().toInt()
        val numberOfStoriesPerTower = binding.newCondoNumberStoriesEt.text.toString().toInt()

        if (numberOfTowers <= 0) {
            Toast.makeText(this,
                "Número de torres não pode ser nulo nem negativo!", Toast.LENGTH_SHORT
            )
                .show()
            return
        }

        if (numberOfStoriesPerTower <= 0) {
            Toast.makeText(this,
                "Número de andares não pode ser nulo nem negativo!", Toast.LENGTH_SHORT
            )
                .show()
            return
        }

        addNewCondoToDatabase(db, numberOfTowers, numberOfStoriesPerTower) { condoId ->
            Toast.makeText(this,
                "Condomínio criado com sucesso.\nRedirecionando para página inicial!", Toast.LENGTH_SHORT
            )
                .show()

            populateCondoWithTowersAndStories(db, condoId)

            val intent = Intent(this, CondoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}