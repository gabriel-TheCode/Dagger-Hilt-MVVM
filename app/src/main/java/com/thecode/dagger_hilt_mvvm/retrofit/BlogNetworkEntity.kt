package com.thecode.dagger_hilt_mvvm.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity (
    @SerializedName("pk")
    @Expose
    var id: Int,

    @SerializedName("pk")
    @Expose
    var title: String,

    @SerializedName("pk")
    @Expose
    var body: String,

    @SerializedName("pk")
    @Expose
    var category: String,

    @SerializedName("pk")
    @Expose
    var image: String
)