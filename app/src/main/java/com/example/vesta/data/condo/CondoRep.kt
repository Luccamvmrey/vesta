package com.example.vesta.data.condo

import com.example.vesta.data.condo.models.Condo
import com.example.vesta.data.condo.models.Tower
import com.example.vesta.data.user.getUserFromLoggedUser
import com.google.firebase.firestore.FirebaseFirestore

fun addNewCondoToDatabase(db: FirebaseFirestore, numberOfTowers: Int,
                          numberOfStoriesPerTower: Int, callback: (condoId: String) -> Unit) {
    getUserFromLoggedUser(db) { _, userId ->
        val newCondo = Condo(
            ArrayList(),
            numberOfTowers,
            numberOfStoriesPerTower,
            userId
        )

        db.collection("condos")
            .add(newCondo)
            .addOnSuccessListener { doc ->
                callback(doc.id)
            }
    }
}

fun updateTowersOnCondo(db: FirebaseFirestore, condoId: String,
                        towers: ArrayList<Tower>, callback: () -> Unit) {
    val condoRef = db.collection("condos").document(condoId)

    condoRef
        .update("towers", towers)
        .addOnSuccessListener {
            callback()
        }
}

fun getCondoFromCondoId(db: FirebaseFirestore, condoId: String,
                        callback: (condo: Condo?, condoId: String) -> Unit) {
    val condoRef = db.collection("condos").document(condoId)

    condoRef
        .get()
        .addOnSuccessListener { doc ->
            val condo = doc.toObject(Condo::class.java)

            callback(condo, condoId)
        }
}