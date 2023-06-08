package com.kma.myapplication.ui.managerclass

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus

import com.kma.myapplication.data.model.ClassItem

import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.data.repository.ClassRepository

import kotlinx.coroutines.launch

class ViewModelClassFragment(app: Application): ViewModel() {
    var listClasItem = MutableLiveData<DataResponse<List<ListClassItem?>>>(DataResponse.DataIdle())
    val repository = ClassRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListClassItem>()
    var value_updtae = MutableLiveData<ListClassItem>()
    lateinit var itemClass : ClassItem
    var clas = MutableLiveData<ClassItem>()
    var clas2 = MutableLiveData<ClassItem>()
    var clas3 = MutableLiveData<ClassItem>()
    fun getListClass() {
        listClasItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListClass()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listClasItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listClasItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteClass(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatClass(item: ListClassItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatClass(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateClass(id:Int,item: ListClassItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateClass(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemClass(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemclass000: "+id)
            itemClass = repository.getItemClass(id)!!
            clas.postValue(itemClass)
            Log.d("TAG", "upData00: "+itemClass)
        }
    }
    fun getItemClass2(id:Int){
        viewModelScope.launch {

            itemClass = repository.getItemClass(id)!!
            clas2.postValue(itemClass)
            Log.d("TAG", "upData00: "+itemClass)
        }
    }
    fun getItemClass3(id:Int){
        viewModelScope.launch {

            itemClass = repository.getItemClass(id)!!
            clas3.postValue(itemClass)
            Log.d("TAG", "upData00: "+itemClass)
        }
    }
}