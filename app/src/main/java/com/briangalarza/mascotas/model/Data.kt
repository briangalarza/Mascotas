package com.briangalarza.mascotas.model

import com.google.gson.annotations.SerializedName

data class Pet(
   // @SerializedName("id")
   // val id:Int?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("status")
    val status:String?

)

