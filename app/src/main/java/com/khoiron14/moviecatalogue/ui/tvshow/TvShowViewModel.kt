package com.khoiron14.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.currentLocale
import com.khoiron14.moviecatalogue.model.tvshow.TvShow
import com.khoiron14.moviecatalogue.service.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by khoiron14 on 7/23/2019.
 */
class TvShowViewModel : ViewModel() {

    private val tvShowResponse = MutableLiveData<List<TvShow>>()

    fun setTvShowList(query: String? = null) {
        val service = RetrofitFactory.service()
        CoroutineScope(Dispatchers.IO).launch {

            val response = if (query == null) {
                service.getTvShowList(currentLocale.toLanguageTag())
            } else {
                service.getTvShowSearchList(currentLocale.toLanguageTag(), query)
            }

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()?.let { tvShowResponse.postValue(it.results) }
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

    fun getTvShowList(): LiveData<List<TvShow>> = tvShowResponse
}