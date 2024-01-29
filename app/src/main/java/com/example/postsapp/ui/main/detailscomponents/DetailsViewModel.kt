package com.example.postsapp.ui.main.detailscomponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquran.common.Resources
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.domain.use_case.GetPostItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private  val postItemUseCase: GetPostItemUseCase):ViewModel() {

    private val _postItemDetails = MutableLiveData<Resources<PostsResponseItem>>()
    val postItemDetails: LiveData<Resources<PostsResponseItem>> = _postItemDetails



      fun getPosts(postId:Int) {

          viewModelScope.launch {
              postItemUseCase(postId).collect() {


                      result ->
                  println("result in vieyodelId : ${postId}")
                  println("result in vieyodel : ${result.data ?: result.message}")
                  _postItemDetails.value = result
              }
          }

    }



}