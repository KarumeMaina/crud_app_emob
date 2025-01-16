package com.example.firebasecrudapp.repository

import com.example.firebasecrudapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")

    suspend fun registerUser(name: String, email: String, password: String): String {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val userId = result.user?.uid ?: ""
        val user = User(id = userId, name = name, email = email, password = password)
        database.child(userId).setValue(user).await()
        return userId
    }

    suspend fun loginUser(email: String, password: String): String {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: ""
    }

    suspend fun getUser(userId: String): User? {
        val snapshot = database.child(userId).get().await()
        return snapshot.getValue(User::class.java)
    }
}

