package com.kma.myapplication.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.TopicItemMainItem
import com.kma.myapplication.data.repository.ArticRepository
import com.kma.myapplication.data.repository.ClassRepository
import com.kma.myapplication.data.repository.TopicRepository
import kotlinx.coroutines.launch

class ViewModelMainFragment(context: Context) : ViewModel() {
    val repository = ClassRepository(context)
    val repository2 = TopicRepository(context)
    val repository3 = ArticRepository(context)
    var listArticle = MutableLiveData<List<ListArticleItem?>>()
    var listDashboardClassItem = MutableLiveData<List<DashboardClass?>>()
    var listTopicItemMainItem = MutableLiveData<List<TopicItemMainItem?>>()
    fun getListDashboardClass(year_id: Int, user_id: Int) {
        viewModelScope.launch {
            listDashboardClassItem.postValue(repository.getListDashboardClass(year_id, user_id))
        }
    }

    fun getListTopicItemMainItem(id: Int) {
        viewModelScope.launch {
            listTopicItemMainItem.postValue(repository2.getListTopicItemMainItem(id))
        }
    }

    fun getListArticle() {
        viewModelScope.launch {
            listArticle.postValue(repository3.getListArtic())
        }
    }
}