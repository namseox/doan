package com.kma.myapplication.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.myapplication.data.model.User
import com.kma.myapplication.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(app: Application) : ViewModel() {
    val repository = UserRepository(app)
    val user = MutableLiveData<User?>()

    fun getUser(userName: String, password: String) {
        viewModelScope.launch {
            val user2 = repository.userLogin(userName, password)
            Log.d("TAG", "getUser:" + userName)
            Log.d("TAG", "getUser:" + user2)
            user.value = user2

        }
    }
}