package com.khoiron14.moviecatalogue

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import androidx.core.os.ConfigurationCompat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by khoiron14 on 7/20/2019.
 */

// get current locale
val currentLocale: Locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

// convert string date to "MMMM yyyy" format, e.g. January 2019
fun convertDate(date: String?): String? {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")

    val formatDate = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val dateConvert = format.parse(date)
    return formatDate.format(dateConvert)
}

// get the current date
fun getDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}

// check device connection
fun connectionAvailable(context: Context): Boolean {
    val manager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = manager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

// show and hide progress bar loading
fun showLoading(state: Boolean, progressBar: ProgressBar) {
    if (state) {
        progressBar.visible()
    } else {
        progressBar.gone()
    }
}