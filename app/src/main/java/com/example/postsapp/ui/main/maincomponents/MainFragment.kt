package com.example.postsapp.ui.main.maincomponents

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentMainBinding
import com.example.postsapp.util.InternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)

        binding.lifecycleOwner =  viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postsViewModel = viewModel
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner){
            Log.i("FrgTagTag","posts : ${it.data}")

        }

        val adapter =PostsRVAdapter{
              viewModel.onPostItemClick(it)
        }

        binding.surahRecyclerView.adapter = adapter

        viewModel.selectedItem.observe(viewLifecycleOwner){postItem ->

            if(null!= postItem){
                 findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(postItem.id))
                 viewModel.onNavigateToSelectedPropertyCompleted()
            }

        }

        viewModel.retryButtonClicked.observe(viewLifecycleOwner){retry ->
            if (InternetConnection.isInternetAvailable(requireContext())) {
                viewModel.getPosts()
            } else {
                // Handle no internet connection case
                Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
            }

        }
    }

}