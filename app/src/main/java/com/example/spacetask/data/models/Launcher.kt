package com.example.spacetask.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Launcher(
    var static_fire_date_utc: String? = "",
    var static_fire_date_unix: Long? = 0,
    var date_unix: Long? = 0,
    var rocket: String? = "",
    var success: Boolean = false,
    var details: String? = "",
    var launchpad: String? = "",
    var flight_number: Int? = 0,
    var name: String? = "",
    var upcoming: Boolean = false,
    var id: String? = "",
    var links: Links? = null
) : Parcelable

@Parcelize
data class Links(
    var patch: Patch? = null,
    var reddit: Reddit? = null,
    var flickr: Flickr? = null,
    var presskit: String? = "",
    var webcast: String? = "",
    var youtube_id: String? = "",
    var article: String? = "",
    var wikipedia: String? = ""
) : Parcelable

@Parcelize
data class Patch(
    var small: String? = "",
    var large: String? = ""
) : Parcelable

@Parcelize
data class Reddit(
    var campaign: String? = "",
    var launch: String? = "",
    var media: String? = "",
    var recovery: String? = ""
) : Parcelable

@Parcelize
data class Flickr(
    var small: ArrayList<String>? = arrayListOf(),
    var original: ArrayList<String>? = arrayListOf()
) : Parcelable