package com.example.postsapp.ui.main.detailscomponents

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.alquran.common.Resources
import com.example.postsapp.R
import com.example.postsapp.data.remote.model.PostsResponseItem
import com.example.postsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsViewModel:DetailsViewModel by activityViewModels()
    private var postItemId :Int =0

    lateinit var binding:FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)


        postItemId = DetailsFragmentArgs.fromBundle(requireArguments()).postItemId

        detailsViewModel.getPosts(postItemId)
        println("result in frag : ${postItemId}")


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.toolbar
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

         actionBar?.title = "Post Details"

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        detailsViewModel.postItemDetails.observe(viewLifecycleOwner){result->
            when (result) {
                is Resources.Success -> {
                    binding.postProgressBar.visibility = View.GONE

                    val postsResponseItem = result.data
                    binding.postId.text  =  "{${postsResponseItem?.id.toString()}}"
                    binding.title.text  = postsResponseItem?.title
                    binding.body.text  = postsResponseItem?.body
                    println("result in fragData : $postsResponseItem")
                    // Update UI with postsResponseItem
                }
                is Resources.Error -> {
                    binding.postProgressBar.visibility = View.GONE

                    val errorMessage = result.message ?: "Unknown error"
                    println("result in fragError : $errorMessage")
                    binding.postErrorMsg.text = errorMessage

                }
                is Resources.Loading -> {
                    println("result in fragLoading")
                   binding.postProgressBar.visibility = View.VISIBLE
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)


    }
}