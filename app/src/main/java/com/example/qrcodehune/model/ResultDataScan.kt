package com.example.qrcodehune.model

import com.google.gson.annotations.SerializedName

data class ResultDataScan(
    @SerializedName("Data")
    val Data: Int,
    @SerializedName("MessageInfo")
    val MessageInfo: String,
    @SerializedName("ResultCode")
    val ResultCode: Int
)