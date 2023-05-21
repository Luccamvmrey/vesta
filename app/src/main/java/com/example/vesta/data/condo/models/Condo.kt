package com.example.vesta.data.condo.models

data class Condo(
    var towers: ArrayList<Tower>? = null,
    val numberOfTowers: Int,
    val numberOfStoriesPerTower: Int,
    val managerId: String,
)
