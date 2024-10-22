package com.ggg.kotlin.hospital_java.dto

data class ResultObjectDto(
    var result:Boolean
    ,var data:Any? = ""
    ,var errMessage:String? = ""
)
