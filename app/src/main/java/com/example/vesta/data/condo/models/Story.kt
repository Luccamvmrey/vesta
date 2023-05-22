package com.example.vesta.data.condo.models

data class Story(
    val number: Int? = null,
    val wasVisited: Boolean? = null,
    var observations: ArrayList<Observation>? = null,
)
