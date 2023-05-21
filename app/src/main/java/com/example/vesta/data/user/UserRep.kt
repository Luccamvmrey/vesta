package com.example.vesta.data.user

import com.example.vesta.data.user.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

private val userAuth = Firebase.auth.currentUser

fun addUserToDatabase(db: FirebaseFirestore, name: String, email: String, position: String,
                      callback: (userId: String) -> Unit) {
    val user = User(name, email, position)

    db.collection("users")
        .add(user)
        .addOnSuccessListener { doc ->
            callback(doc.id)
        }
}

fun getUserFromLoggedUser(db: FirebaseFirestore, callback: (user: User, userId: String) -> Unit) {
    val usersRef = db.collection("users")

    usersRef
        .whereEqualTo("email", userAuth?.email.toString())
        .get()
        .addOnSuccessListener { sp ->
            sp.forEach { doc ->
                val userId = doc.id
                val user = doc.toObject(User::class.java)

                callback(user, userId)
            }
        }
}