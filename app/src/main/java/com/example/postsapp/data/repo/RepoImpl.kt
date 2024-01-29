package com.example.postsapp.data.repo

import com.example.postsapp.data.remote.PostsAPIService
import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.domain.repo.Repo
import javax.inject.Inject

class RepoImpl @Inject constructor(private val apiService: PostsAPIService):Repo {
    override suspend fun getPosts(): PostsResponse {
        return apiService.getPosts()
    }

    override suspend fun getPostDetails(userId: Int): PostsResponseItem {
        return apiService.getPostDetails(userId)
    }
}