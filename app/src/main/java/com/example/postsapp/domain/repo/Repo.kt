package com.example.postsapp.domain.repo

import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.data.remote.model.PostsResponseItem
import retrofit2.http.Path

interface Repo {

    suspend fun getPosts() : PostsResponse
    suspend fun getPostDetails(userId :Int) : PostsResponseItem
}