package com.irfan.sadaparcel.inventory

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface InventoryRemoteApi {
    @GET("/items")
    suspend fun getInventoryItems(): Response<ResponseBody>
}
