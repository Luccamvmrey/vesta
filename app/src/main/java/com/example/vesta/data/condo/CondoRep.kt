package com.example.vesta.data.condo

import com.example.vesta.data.condo.models.Condo
import com.example.vesta.data.user.getUserFromLoggedUser
import com.google.firebase.firestore.FirebaseFirestore

fun createNewCondo(db: FirebaseFirestore, numberOfTowers: Int,
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