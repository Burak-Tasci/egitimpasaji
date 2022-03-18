package com.tsci.eitimpasaj.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsci.eitimpasaj.data.DataSource

class AppViewModel : ViewModel(){

    val urls = DataSource.urls

    fun getTargetUrl(target: String): String? {
        return urls.get(target)
    }

}