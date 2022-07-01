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
        return InventoryRemoteService(FakeInventoryRemoteApiWithTimeOutException())
    }

}

class FakeInventoryRemoteApiWithTimeOutException() : InventoryRemoteApi {
    override suspend fun getInventoryItems(): Response<ResponseBody> {
        throw TimeoutException()
    }

}
