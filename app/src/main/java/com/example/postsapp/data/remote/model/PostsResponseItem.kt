package com.example.postsapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable