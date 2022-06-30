package com.irfan.sadaparcel.inventory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteApiConfiguration {


 fun <T> getRemoteApi(clazz: Class<T>):T{
    return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl("https://mocki.io")
         .build()
         .create(clazz)
 }

}