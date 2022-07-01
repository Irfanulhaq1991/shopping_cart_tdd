package com.irfan.sadaparcel.inventory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.concurrent.TimeoutException

class InventoryRemoteService(private val inventoryApi: InventoryRemoteApi) : InventoryService {
    override suspend fun getInventoryItems(): List<InventoryItemWithQuantity> {
        return try {
            val apiResponse = inventoryApi.getInventoryItems()
            if (!apiResponse.isSuccessful) {
                throw  AppException("Error occurred")
            }

            val parsedData = parsJsonBody(apiResponse)
            parsedData
        } catch (e: TimeoutException) {
            throw AppException("No Internet")
        }
    }

    private fun parsJsonBody(response: Response<ResponseBody>): List<InventoryItemWithQuantity> {
        val typToken = object : TypeToken<List<InventoryItemWithQuantity>>() {}.type
        val gson = Gson()
        val responseBody = response.body()!!.string()
        return gson.fromJson(responseBody, typToken)

    }

}