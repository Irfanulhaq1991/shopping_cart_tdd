package com.irfan.sadaparcel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteApiConfiguration {

// I could added okttp3 logging interceptor for observability
 fun <T> getRemoteApi(clazz: Class<T>):T{
    return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl("https://mocki.io") // replace host with actual host
         .build()
         .create(clazz)
 }

}