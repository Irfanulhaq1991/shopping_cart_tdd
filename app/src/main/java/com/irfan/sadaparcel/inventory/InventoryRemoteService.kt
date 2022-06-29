package com.irfan.sadaparcel.inventory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.concurrent.TimeoutException

class InventoryRemoteService(private val inventoApi: InventoryRemoteApi) :InventoryService {
    override fun getInventoryItems(): List<InventoryItemWithQuantity> {
        return try {
            val apiResponse = inventoApi.getInventoryItems()
            if (!apiResponse.isSuccessful) {
                throw  AppException("Error occurred")
            }

            val parsedData = parsAndGetBody(apiResponse)
            parsedData
        } catch (e: TimeoutException) {
            throw AppException("No Internet")
        }
    }

    private fun parsAndGetBody(response: Response<ResponseBody>): List<InventoryItemWithQuantity> {
        val typToken = object : TypeToken<List<InventoryItemWithQuantity>>() {}.type
        val gson = Gson()
        return gson.fromJson(response.body()!!.string(), typToken)

    }

}