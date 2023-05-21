package com.example.vesta.data.user

import com.example.vesta.data.user.model.User
import com.google.firebase.firestore.FirebaseFirestore

fun addUserToDatabase(db: FirebaseFirestore, name: String, email: String, position: String,
                      callback: (userId: String) -> Unit) {
    val user = User(name, email, position)

    db.collection("users")
        .add(user)
        .addOnSuccessListener { doc ->
            callback(doc.id)
        }
}