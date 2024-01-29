package com.example.postsapp.data.remote

import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.data.remote.model.PostsResponseItem
import retrofit2.http.GET
import retrofit2.http.Path


interface PostsAPIService {


    @GET("posts")
    suspend fun getPosts() : PostsResponse

    @GET("posts/{userId}")
    suspend fun getPostDetails(@Path("userId")
                                   userId :Int) : PostsResponseItem

}