package com.kma.myapplication.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kma.myapplication.data.model.User
import com.kma.myapplication.data.model.Year
import com.kma.myapplication.data.repository.YearRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class SharedViewModel@Inject constructor(context: Context): ViewModel() {
    companion object : SingletonHolder<SharedViewModel, Context>(::SharedViewModel)
    lateinit var user :User
    var listYearGetId = ArrayList<Year>()
    var listYear = MutableLiveData<List<Year>>()
    var yearApi = YearRepository(context)
    var arrayYear = arrayListOf<String>()
    var yearId :Int = 0

    fun getListYear(){
        viewModelScope.launch {
            listYearGetId = yearApi.getListYear() as ArrayList<Year>
            listYear.postValue(listYearGetId)

        }
    }
}