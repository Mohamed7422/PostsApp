package com.example.postsapp.bindingAdapters

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alquran.common.Resources
import com.example.postsapp.data.remote.model.PostsResponse
import com.example.postsapp.ui.main.maincomponents.PostsRVAdapter


@BindingAdapter("setVisibleLoading")
fun setVisibleLoading(view: View,resources: Resources<*>){
    if (resources is Resources.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}


@BindingAdapter("setVisibleListSuccess")
fun RecyclerView.setVisibleListSuccess(resources: Resources<PostsResponse>?){

    visibility = if(resources is Resources.Success && !resources.data.isNullOrEmpty()) View.VISIBLE else
        View.GONE
    if (resources is Resources.Success){
        val adapter = this.adapter as? PostsRVAdapter
        println("data From veu : ${resources.data}")
        adapter?.submitList(resources.data)
    }

}

@BindingAdapter("setVisibleError")
fun setVisibleError(view: TextView, resources: Resources<*>){
    if (resources is Resources.Error ){
        view.visibility = View.VISIBLE
        view.text =  "Unexpected Error Check your Internet Connection"
    }else{
        view.visibility = View.GONE
    }
}
@BindingAdapter("setVisibleRetryConnectionBtn")
fun setVisibleRetryConnectionBtn(view: Button, resources: Resources<*>){
    if (resources is Resources.Error ){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}
