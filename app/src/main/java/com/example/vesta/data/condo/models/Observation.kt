package com.example.vesta.data.condo.models

import com.example.vesta.data.user.model.User
import java.util.Date

data class Observation(
    val imageUrl: String? = null,
    val description: String? = null,
    val date: Date? = null,
    val user: User? = null,
)
