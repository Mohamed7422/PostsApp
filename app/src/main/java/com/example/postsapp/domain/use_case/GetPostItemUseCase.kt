package com.example.postsapp.domain.use_case

import com.example.alquran.common.Resources
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetPostItemUseCase @Inject constructor(private val repo: Repo) {


    operator fun invoke(userId:Int):Flow<Resources<PostsResponseItem>> = flow {

        emit(Resources.Loading<PostsResponseItem>())

        try {
            val postItem = repo.getPostDetails(userId)
            emit(Resources.Success<PostsResponseItem>(postItem))

        }catch (e:HttpException){
            emit(Resources.Error<PostsResponseItem>(e.localizedMessage?:"Unexpected Error"))

        }catch (e:IOException){
            emit(Resources.Error<PostsResponseItem>(e.localizedMessage?:"Unexpected Error"))

        }


    }
}