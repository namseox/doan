package com.kma.myapplication.ui.article

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.repository.ArticRepository
import kotlinx.coroutines.launch

class ViewModelArticleFragment(app: Application): ViewModel() {
    var listArticleItem = MutableLiveData<DataResponse<List<ListArticleItem?>>>(DataResponse.DataIdle())
    val repository = ArticRepository(app)
    var value_delete = MutableLiveData<Int>()
    var value_creat = MutableLiveData<ListArticleItem>()
    var value_updtae = MutableLiveData<ListArticleItem>()
    lateinit var itemBook : ArticleItem
    var article = MutableLiveData<ArticleItem>()
    var article2 = MutableLiveData<ArticleItem>()
    var article3 = MutableLiveData<ArticleItem>()
    fun getListBook() {
        listArticleItem.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result  = repository.getListArtic()
            Log.d("123", "getListStaff: "+result)
            if (result!!.isNotEmpty()) {
                listArticleItem.postValue(DataResponse.DataSuccess(result))
            } else {
                listArticleItem.postValue(DataResponse.DataError())
            }

        }
    }

    fun deleteBook(id: Int){
        viewModelScope.launch {
            value_delete.postValue(repository.delete(id))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun creatBook(item: ListArticleItem){
        viewModelScope.launch {
            value_creat.postValue(repository.creatArtic(item))
//            Log.d("TAG", "deleteStaff: "+)
        }
    }

    fun updateBook(id:Int,item: ListArticleItem){
        viewModelScope.launch {
            value_updtae.postValue(repository.updateBook(id,item))
            Log.d("TAG", "upData0000: "+value_updtae)
        }
    }
    fun getItemBook(id:Int){
        viewModelScope.launch {
            Log.d("TAG", "getItemBook000: "+id)
            itemBook = repository.getItemArtic(id)!!
            article.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
    fun getItemBook2(id:Int){
        viewModelScope.launch {

            itemBook = repository.getItemArtic(id)!!
            article2.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
    fun getItemBook3(id:Int){
        viewModelScope.launch {

            itemBook = repository.getItemArtic(id)!!
            article3.postValue(itemBook)
            Log.d("TAG", "upData00: "+itemBook)
        }
    }
}