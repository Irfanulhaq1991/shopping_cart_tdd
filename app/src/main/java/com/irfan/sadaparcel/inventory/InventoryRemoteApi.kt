package com.irfan.sadaparcel.inventory

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface InventoryRemoteApi {

   // @GET("/items")
    @GET("/v1/af9928d3-3fdb-43e6-bd61-5296288756f6") // mocked api with same response
   suspend fun getInventoryItems(): Response<ResponseBody>
}
