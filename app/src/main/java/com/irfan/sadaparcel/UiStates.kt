package com.irfan.sadaparcel

import android.provider.ContactsContract

sealed class UiStates {
    object Loading : UiStates()
    object HideLoading : UiStates()
    data class Error(val message: String? = "" ):UiStates()

    //Not decided yet the type of data which success state  could carry to view
    data class Success(val data:Any? = null,val message:String = ""):UiStates()
}
