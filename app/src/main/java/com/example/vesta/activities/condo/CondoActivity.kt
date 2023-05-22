package com.example.vesta.activities.condo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vesta.R

class CondoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condo)

        supportActionBar?.hide()
    }
}