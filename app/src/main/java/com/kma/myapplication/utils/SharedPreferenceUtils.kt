package com.kma.myapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kma.myapplication.data.model.StaffItem
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferenceUtils @Inject constructor(context: Context) {
    private val MYAPPLICATION = "MY_APPLICATION"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(MYAPPLICATION, Context.MODE_PRIVATE)



    //lưu đối tượng
    inline fun <reified T> getObjModel(): T? {
        val value = getStringValue("Key_Obj_${T::class.java.name}")
        return if (value != null && value.isNotEmpty()) {
            Gson().fromJson(value, T::class.java)
        } else {
            null
        }
    }

    inline fun <reified T> setObjModel(value: T?) {
        if (value != null) {
            putStringValue("Key_Obj_${T::class.java.name}", Gson().toJson(value))
        }
    }
    fun putStringValue(key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value).apply()
    }

    fun getStringValue(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }


    //check number name playlist
    fun putNumberNamePlaylist(value: Int) {
        putNumberNamePlaylistValue("numberNamePlayList", value)
    }

    fun getNumberNamePlaylist(): Int {
        return getNumberNamePlaylistValue("numberNamePlayList")
    }

    private fun putNumberNamePlaylistValue(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value).apply()
    }

    private fun getNumberNamePlaylistValue(key: String): Int {
        return sharedPreferences.getInt(key, 1)
    }
    //check isSuccessAddArchive
    fun putIsSuccessAddArchive(value: Boolean) {
        putIsSuccessAddArchiveValue("isSuccessAddArchive", value)
    }

    fun getIsSuccessAddArchive(): Boolean {
        return getIsSuccessAddArchiveValue("isSuccessAddArchive")
    }

    private fun putIsSuccessAddArchiveValue(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    private fun getIsSuccessAddArchiveValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
    //
    fun getAcceptPolicy():Boolean{
        return getBooleanValue("isAcceptPolicy")
    }
    fun getBooleanValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
    fun putAcceptPolicy(value: Boolean){
        putBooleanValue("isAcceptPolicy",value)
    }
    fun putBooleanValue(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    companion object : SingletonHolder<SharedPreferenceUtils, Context>(::SharedPreferenceUtils)




}