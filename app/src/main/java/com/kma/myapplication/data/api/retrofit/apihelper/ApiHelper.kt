package com.kma.myapplication.data.api.retrofit.apihelper

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kma.myapplication.data.api.base.BaseRetrofitHelper
import com.kma.myapplication.data.api.retrofit.year.YearApi
import com.kma.myapplication.data.api.retrofit.artic.ArticApi
import com.kma.myapplication.data.api.retrofit.book.BookApi
import com.kma.myapplication.data.api.retrofit.classs.ClassApi
import com.kma.myapplication.data.api.retrofit.education.EducationApi
import com.kma.myapplication.data.api.retrofit.exam.ExamApi
import com.kma.myapplication.data.api.retrofit.invention.InventionApi
import com.kma.myapplication.data.api.retrofit.mark.MarkApi
import com.kma.myapplication.data.api.retrofit.room.RoomApi
import com.kma.myapplication.data.api.retrofit.staff.StaffApi
import com.kma.myapplication.data.api.retrofit.subject.SubjectApi
import com.kma.myapplication.data.api.retrofit.topic.TopicApi
import com.kma.myapplication.data.api.retrofit.user.LoginApi
import com.kma.myapplication.utils.SingletonHolder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiHelper private constructor(context: Context) : BaseRetrofitHelper(context) {
        private val BASE_URL = "http://192.130.15.109:8001/"
//    private val BASE_URL = "http://127.0.0.1:5173"
    var loginApi: LoginApi
    var staffApi: StaffApi
    var bookApi:BookApi
    var articApi :ArticApi
    var classApi:ClassApi
    var educationApi:EducationApi
    var examApi: ExamApi
    var inventionApi :InventionApi
    var markApi:MarkApi
    var roomApi: RoomApi
    var subjectApi :SubjectApi
    var apiYear : YearApi
    var topicApi :TopicApi

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(
                CoroutineCallAdapterFactory()
            ).client(okHttpClient!!).build()
        loginApi = retrofit.create(LoginApi::class.java)
        staffApi = retrofit.create(StaffApi::class.java)
        bookApi = retrofit.create(BookApi::class.java)
        articApi = retrofit.create(ArticApi::class.java)
        classApi = retrofit.create(ClassApi::class.java)
        educationApi = retrofit.create(EducationApi::class.java)
        examApi = retrofit.create(ExamApi::class.java)
        inventionApi = retrofit.create(InventionApi::class.java)
        markApi = retrofit.create(MarkApi::class.java)
        roomApi = retrofit.create(RoomApi::class.java)
        subjectApi = retrofit.create(SubjectApi::class.java)
        apiYear = retrofit.create(YearApi::class.java)
        topicApi = retrofit.create(TopicApi::class.java)
    }

    companion object : SingletonHolder<ApiHelper, Context>(::ApiHelper)

}