package com.kma.myapplication.ui.sach

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.repository.BookRepository
import kotlinx.coroutines.launch

class ViewModelBookFragment(app: Application):ViewModel() {
    var listBookItem = MutableLiveData<DataResponse<List<ListBookItem?>>>(DataResponse.DataIdle())
    val repository = BookRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListBookItem>()
    var value_updtae = MutableLiveData<ListBookItem>()
    lateinit var itemBook : Book
    var book =MutableLiveData<Book>()
    var book2 =MutableLiveData<Book>()
    var book3 =MutableLiveData<Book>()
    fun getListBook() {
        listBookItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListBook()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listBookItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listBookItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteBook(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatBook(item: ListBookItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatBook(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateBook(id:Int,item: ListBookItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateBook(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemBook(id:Int){
        viewModelScope.launch {
            itemBook = repository.getItemBook(id)!!
            book.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
    fun getItemBook2(id:Int){
        viewModelScope.launch {

            itemBook = repository.getItemBook(id)!!
            book2.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
    fun getItemBook3(id:Int){
        viewModelScope.launch {

            itemBook = repository.getItemBook(id)!!
            book3.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
}