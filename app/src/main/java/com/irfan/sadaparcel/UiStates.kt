package com.irfan.sadaparcel

import android.provider.ContactsContract

sealed class UiStates {
    object Loading : UiStates()
    object HideLoading : UiStates()
    //Not decided yet the type of data which success state  could carry to view
    data class Success(val message:String = "",val data:Any? = null):UiStates()
}
