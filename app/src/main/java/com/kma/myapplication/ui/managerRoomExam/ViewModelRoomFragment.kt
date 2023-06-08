package com.kma.myapplication.ui.managerRoomRoom

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.RoomItem
import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.data.repository.RoomRepository
import kotlinx.coroutines.launch

class ViewModelRoomFragment(app: Application): ViewModel() {
    var listRoomItem = MutableLiveData<DataResponse<List<ListRoomItem?>>>(DataResponse.DataIdle())
    val repository = RoomRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListRoomItem>()
    var value_updtae = MutableLiveData<ListRoomItem>()
    lateinit var itemRoom : RoomItem
    var Room = MutableLiveData<RoomItem>()
    var Room2 = MutableLiveData<RoomItem>()
    var Room3 = MutableLiveData<RoomItem>()
    fun getListRoom() {
        listRoomItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListRoom()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listRoomItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listRoomItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteRoom(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatRoom(item: ListRoomItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatRoom(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateRoom(id:Int,item: ListRoomItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateRoom(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemRoom(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemRoom000: "+id)
            itemRoom = repository.getItemRoom(id)!!
            Room.postValue(itemRoom)
            Log.d("TAG", "upData00: "+itemRoom)
        }
    }
    fun getItemRoom2(id:Int){
        viewModelScope.launch {

            itemRoom = repository.getItemRoom(id)!!
            Room2.postValue(itemRoom)
            Log.d("TAG", "upData00: "+itemRoom)
        }
    }
    fun getItemRoom3(id:Int){
        viewModelScope.launch {

            itemRoom = repository.getItemRoom(id)!!
            Room3.postValue(itemRoom)
            Log.d("TAG", "upData00: "+itemRoom)
        }
    }
}