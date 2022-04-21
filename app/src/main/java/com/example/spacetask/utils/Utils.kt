package com.example.spacetask.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

object Utils {

    fun readMore(activity: Activity?, wikiUrl: String) {
        val intentView = Intent(Intent.ACTION_VIEW)
        intentView.data = Uri.parse(wikiUrl)
        intentView.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        activity?.startActivity(intentView)
    }
}