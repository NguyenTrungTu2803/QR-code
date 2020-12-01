package com.example.qrcodehune.model

import com.google.gson.annotations.SerializedName

data class UsersScanCodeModel(
    @SerializedName("ActionName")
    val ActionName: String,
    @SerializedName("ClientName")
    val ClientName: String,
    @SerializedName("Parameter")
    val Parameter: Parameter,
    @SerializedName("ServiceCode")
    val ServiceCode: Int
)