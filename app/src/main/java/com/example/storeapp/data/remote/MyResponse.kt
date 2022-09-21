package com.example.storeapp.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyResponse<T> : Serializable {

    @SerializedName("data")
    var data: T? = null

    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("status")
    var status: Int? = null
}
