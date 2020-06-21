package com.briangalarza.mascotas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pet(
    @SerializedName("id")
    val id:Long?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("status")
    val status:String?,
    @SerializedName("category")
    val category:Category?,
    @SerializedName("photoUrls")
    val photos:ArrayList<String>


    ):Parcelable

@Parcelize
data class Category(
    @SerializedName("name")
    val name:String?
):Parcelable


