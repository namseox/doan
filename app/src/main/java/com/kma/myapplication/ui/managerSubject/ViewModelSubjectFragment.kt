package com.kma.myapplication.ui.managerSubject

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.data.model.ListSubjectItem
import com.kma.myapplication.data.model.SubjectItem
import com.kma.myapplication.data.repository.ExamRepository
import com.kma.myapplication.data.repository.SubjectRepository
import kotlinx.coroutines.launch

class ViewModelSubjectFragment (app: Application): ViewModel() {
    var listSubjectItem = MutableLiveData<DataResponse<List<ListSubjectItem?>>>(DataResponse.DataIdle())
    val repository = SubjectRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListSubjectItem>()
    var value_updtae = MutableLiveData<ListSubjectItem>()
    lateinit var itemSubject : SubjectItem
    var Subject = MutableLiveData<SubjectItem>()
    var Subject2 = MutableLiveData<SubjectItem>()
    var Subject3 = MutableLiveData<SubjectItem>()
    fun getListSubject() {
        listSubjectItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListSubject()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listSubjectItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listSubjectItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteSubject(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatSubject(item: ListSubjectItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatSubject(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateSubject(id:Int,item: ListSubjectItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateSubject(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemSubject(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemSubject000: "+id)
            itemSubject = repository.getItemSubject(id)!!
            Subject.postValue(itemSubject)
            Log.d("TAG", "upData00: "+itemSubject)
        }
    }
    fun getItemSubject2(id:Int){
        viewModelScope.launch {

            itemSubject = repository.getItemSubject(id)!!
            Subject2.postValue(itemSubject)
            Log.d("TAG", "upData00: "+itemSubject)
        }
    }
    fun getItemSubject3(id:Int){
        viewModelScope.launch {

            itemSubject = repository.getItemSubject(id)!!
            Subject3.postValue(itemSubject)
            Log.d("TAG", "upData00: "+itemSubject)
        }
    }
}