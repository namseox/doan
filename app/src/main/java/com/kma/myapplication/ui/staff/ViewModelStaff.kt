package com.kma.myapplication.ui.staff

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.data.reponstory.StaffReponstory
import com.kma.myapplication.data.reponstory.UserReponstory
import kotlinx.coroutines.launch

class ViewModelStaff(app:Application) : ViewModel() {
    var listStaffItem = MutableLiveData<DataResponse<List<StaffItem?>>>(DataResponse.DataIdle())
    val repository = StaffReponstory(app)
    fun getListStaff() {
        listStaffItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
//            val result = audioRepository.getAudiosByNamePlaylist(namePlaylist)
            var result  = repository.getListStaff()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listStaffItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listStaffItem.postValue(DataResponse.DataError())
            }

        }
    }
}