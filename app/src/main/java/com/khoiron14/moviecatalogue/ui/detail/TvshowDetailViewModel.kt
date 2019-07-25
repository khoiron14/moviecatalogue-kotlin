package com.khoiron14.moviecatalogue.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.model.tvshow.Tvshow
import com.khoiron14.moviecatalogue.service.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by khoiron14 on 7/23/2019.
 */
class TvshowDetailViewModel : ViewModel() {

    private val tvshow = MutableLiveData<Tvshow>()

    fun setTvshow(id: Int) {
        val service = RetrofitFactory.service()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTvshow(id)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()?.let { tvshow.postValue(it) }
                    } else {
                        error("Error ${response.code()}")
                    }
                } catch (e: HttpException) {
                    error("Exception ${e.message()}")
                } catch (e: Throwable) {
                    error("Oops something went wrong")
                }
            }
        }
    }

    fun getTvshow(): LiveData<Tvshow> = tvshow
}