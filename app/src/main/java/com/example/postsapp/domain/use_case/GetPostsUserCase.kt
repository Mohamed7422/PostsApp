package com.example.postsapp.domain.use_case

import com.example.alquran.common.Resources
import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetPostsUserCase @Inject constructor(private val repo: Repo) {


    operator fun invoke():Flow<Resources<PostsResponse>> = flow {

        emit(Resources.Loading<PostsResponse>())

        try {
            val posts = repo.getPosts()
            emit(Resources.Success<PostsResponse>(posts))

        }catch (e:HttpException){
            emit(Resources.Error<PostsResponse>(e.localizedMessage?:"Unexpected Error"))

        }catch (e:IOException){
            emit(Resources.Error<PostsResponse>(e.localizedMessage?:"Unexpected Error"))

        }


    }
}