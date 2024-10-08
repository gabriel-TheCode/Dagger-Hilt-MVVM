package com.thecode.dagger_hilt_mvvm.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Blog(
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
) : Parcelable