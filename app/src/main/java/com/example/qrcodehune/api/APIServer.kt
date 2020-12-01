package com.example.qrcodehune.api

import com.example.qrcodehune.model.ResultDataScan
import com.example.qrcodehune.model.UsersScanCodeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIServer {
    companion object{
        const val BASE_URL = "https://prdapp.hunegroup.com/api/"
    }

    @POST("HuneGatewayMobile/ProcessAPI")
    fun callUserScan(@Body red: UsersScanCodeModel) : Call<ResultDataScan>
}