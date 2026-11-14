package com.esan.diarioespacial.data.remote.apod

import com.google.gson.annotations.SerializedName

/** Todo nullable: si la API no envía un campo, la app no se cae. */
data class ApodDto(
    @SerializedName("date") val date: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("hdurl") val hdurl: String?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("copyright") val copyright: String?
)
