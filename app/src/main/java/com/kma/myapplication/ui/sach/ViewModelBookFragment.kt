package com.kma.myapplication.ui.sach

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hola360.m3uplayer.data.response.DataResponse
import com.kma.myapplication.data.model.CreateUser
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.repository.StaffRepository

class ViewModelBookFragment(app: Application):ViewModel() {
    var listStaffItem = MutableLiveData<DataResponse<List<ListBookItem?>>>(DataResponse.DataIdle())
    val repository = StaffRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<CreateUser>()
    var value_updtae = MutableLiveData<StaffItem>()
}