package com.example.firebasecrudapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firebasecrudapp.repository.FirebaseRepository

class UserViewModel: ViewModel {
    private val repositoey = FirebaseRepository()

    fun registerUser(name:String, email:String, password:String, nResult: (Boolean, String?) -> Unit) {

    })
}