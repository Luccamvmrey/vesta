package com.example.vesta.data.condo.models

data class Condo(
    var towers: ArrayList<Tower>? = null,
    val numberOfTowers: Int? = null,
    val numberOfStoriesPerTower: Int? = null,
    val managerId: String? = null,
)
