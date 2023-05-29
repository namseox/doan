package com.kma.myapplication.ui.staff

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.CreateUser
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.data.repository.StaffRepository
import kotlinx.coroutines.launch

class ViewModelStaffFragment(app:Application) : ViewModel() {
    var listStaffItem = MutableLiveData<DataResponse<List<StaffItem?>>>(DataResponse.DataIdle())
    val repository = StaffRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<CreateUser>()
    var value_updtae = MutableLiveData<StaffItem>()
    lateinit var staffItem : StaffItem
    var checkUpdate:Boolean = false
    fun getListStaff() {
        listStaffItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListStaff()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listStaffItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listStaffItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteStaff(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.deleteStaff(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatStaff(staff: UserX){
        viewModelScope.launch {
            value_creat.postValue(repository.createUser(staff))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateStaff(id:Int,staff: UserX){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateUser(id,staff))
            Log.d("TAG", "upData00: "+value_updtae)
        }
    }
}