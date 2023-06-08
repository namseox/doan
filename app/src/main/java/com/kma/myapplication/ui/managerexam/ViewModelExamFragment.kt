package com.kma.myapplication.ui.managerexam

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.data.repository.ExamRepository

import kotlinx.coroutines.launch

class ViewModelExamFragment(app: Application): ViewModel() {
    var listExamItem = MutableLiveData<DataResponse<List<ListExamItem?>>>(DataResponse.DataIdle())
    val repository = ExamRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListExamItem>()
    var value_updtae = MutableLiveData<ListExamItem>()
    lateinit var itemExam : ExamItem
    var exam = MutableLiveData<ExamItem>()
    var exam2 = MutableLiveData<ExamItem>()
    var exam3 = MutableLiveData<ExamItem>()
    fun getListExam() {
        listExamItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListExam()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listExamItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listExamItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteExam(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatExam(item: ListExamItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatExam(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateExam(id:Int,item: ListExamItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateExam(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemExam(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemExam000: "+id)
            itemExam = repository.getItemExam(id)!!
            exam.postValue(itemExam)
            Log.d("TAG", "upData00: "+itemExam)
        }
    }
    fun getItemExam2(id:Int){
        viewModelScope.launch {

            itemExam = repository.getItemExam(id)!!
            exam2.postValue(itemExam)
            Log.d("TAG", "upData00: "+itemExam)
        }
    }
    fun getItemExam3(id:Int){
        viewModelScope.launch {

            itemExam = repository.getItemExam(id)!!
            exam3.postValue(itemExam)
            Log.d("TAG", "upData00: "+itemExam)
        }
    }
}