package com.example.postsapp.ui.main.maincomponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquran.common.Resources
import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.domain.use_case.GetPostsUserCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val postsUserCase: GetPostsUserCase):ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<Resources<PostsResponse>>()
    val navigateToSelectedProperty: LiveData<Resources<PostsResponse>> = _navigateToSelectedProperty


    private val _selectedItem  = MutableLiveData<PostsResponseItem>()
    val selectedItem :LiveData<PostsResponseItem> = _selectedItem

    private val _retryButtonClicked = MutableLiveData<Unit>()
    val retryButtonClicked: LiveData<Unit> get() = _retryButtonClicked

    init {
        getPosts()

    }


      fun getPosts() {
        viewModelScope.launch {
            postsUserCase().collect{
                    result ->
                println("result in vieyodel : ${result.data?:result.message}")
                _navigateToSelectedProperty.value = result
            }
        }
    }

    fun onPostItemClick(postsResponseItem: PostsResponseItem) {
        _selectedItem.value = postsResponseItem
    }

    fun onNavigateToSelectedPropertyCompleted(){
        _selectedItem.value = null
    }



    fun onRetryButtonClick() {
        _retryButtonClicked.value = Unit
    }

}