package com.irfan.sadaparcel.inventory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.util.concurrent.TimeoutException

class InventoryRemoteServiceShould : InventoryServiceContractTests() {

    override fun withDataItems(inventoryItems: List<InventoryItemWithQuantity>): InventoryService {
        return InventoryRemoteService(FakeInventoryRemoteApiWithData(inventoryItems))
    }

    override fun withAppException(): InventoryService {
        return InventoryRemoteService(FakeInventoryRemoteApiWithNoInternetError())
    }

}

class FakeInventoryRemoteApiWithData(private val inventoryItems: List<InventoryItemWithQuantity>) :
    InventoryRemoteApi {
    override fun getInventoryItems(): Response<ResponseBody> {
        return Response.success(createResponseBody())
    }

    private fun createResponseBody(): ResponseBody {
        val typToken = object : TypeToken<List<InventoryItemWithQuantity>>() {}.type
        val jsonData = Gson().toJson(inventoryItems, typToken)
        val contentType = "application/json; charset=utf-8".toMediaType()
        return jsonData.toResponseBody(contentType)
    }
}

class FakeInventoryRemoteApiWithNoInternetError() : InventoryRemoteApi {
    override fun getInventoryItems(): Response<ResponseBody> {
        throw TimeoutException()
    }

}
