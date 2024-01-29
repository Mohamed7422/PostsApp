package com.example.postsapp.ui.main.maincomponents


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.databinding.PostItemBinding

class PostsRVAdapter(  private val listener: (PostsResponseItem) -> Unit): ListAdapter<PostsResponseItem,PostsRVAdapter.PostViewHolder>(DiffCallBAck)
    {



        init {
            setHasStableIds(true)
        }

   companion object DiffCallBAck: DiffUtil.ItemCallback<PostsResponseItem>(){
       override fun areItemsTheSame(
           oldItem: PostsResponseItem,
           newItem: PostsResponseItem
       ): Boolean {
           return oldItem === newItem
       }

       override fun areContentsTheSame(
           oldItem: PostsResponseItem,
           newItem: PostsResponseItem
       ): Boolean {
           return oldItem.id == newItem.id
       }

   }


    inner class PostViewHolder(private var  binding: PostItemBinding):ViewHolder(binding.root){
        fun bind(item: PostsResponseItem?) {
            binding.post = item
            binding.executePendingBindings()
        }

        init {
             binding.root.setOnClickListener{
                 val position =bindingAdapterPosition
                 if (position != RecyclerView.NO_POSITION){
                     val item = getItem(position)
                      if (item!=null){
                         listener.invoke(item)
                      }
                 }
             }
        }



    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
              return PostViewHolder(
                  PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
              )
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item)
        }
    }