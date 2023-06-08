package com.kma.myapplication.ui.managerMark

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.MarkItem
import com.kma.myapplication.data.model.ListMarkItem
import com.kma.myapplication.data.repository.MarkRepository
import kotlinx.coroutines.launch

class ViewModelMarkFragment (app: Application): ViewModel() {
    var listMarkItem = MutableLiveData<DataResponse<List<ListMarkItem?>>>(DataResponse.DataIdle())
    val repository = MarkRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListMarkItem>()
    var value_updtae = MutableLiveData<ListMarkItem>()
    lateinit var itemMark : MarkItem
    var Mark = MutableLiveData<MarkItem>()
    var Mark2 = MutableLiveData<MarkItem>()
    var Mark3 = MutableLiveData<MarkItem>()
    fun getListMark() {
        listMarkItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListMark()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listMarkItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listMarkItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteMark(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatMark(item: ListMarkItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatMark(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateMark(id:Int,item: ListMarkItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateMark(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemMark(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemMark000: "+id)
            itemMark = repository.getItemMark(id)!!
            Mark.postValue(itemMark)
            Log.d("TAG", "upData00: "+itemMark)
        }
    }
    fun getItemMark2(id:Int){
        viewModelScope.launch {

            itemMark = repository.getItemMark(id)!!
            Mark2.postValue(itemMark)
            Log.d("TAG", "upData00: "+itemMark)
        }
    }
    fun getItemMark3(id:Int){
        viewModelScope.launch {

            itemMark = repository.getItemMark(id)!!
            Mark3.postValue(itemMark)
            Log.d("TAG", "upData00: "+itemMark)
        }
    }
}