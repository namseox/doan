package com.kma.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.myapplication.data.model.User
import kotlinx.coroutines.launch

class ViewModelActivityMain: ViewModel() {
    var user = MutableLiveData<User>()

    fun getUser(user1:User){
        viewModelScope.launch {
            user.value = user1
        }
    }
}