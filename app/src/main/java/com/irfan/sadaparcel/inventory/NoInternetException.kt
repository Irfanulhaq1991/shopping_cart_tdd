package com.irfan.sadaparcel.inventory

import java.lang.Exception
// App Exception to decouple the underlying errors
class AppException(message:String = "") : Exception(message)
