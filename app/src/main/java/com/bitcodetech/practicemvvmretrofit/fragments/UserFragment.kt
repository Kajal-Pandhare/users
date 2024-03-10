package com.bitcodetech.practicemvvmretrofit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.practicemvvmretrofit.adapter.UserAdapter
import com.bitcodetech.practicemvvmretrofit.databinding.ActivityMainBinding
import com.bitcodetech.practicemvvmretrofit.databinding.UserFragmentBinding
import com.bitcodetech.practicemvvmretrofit.factory.MyViewModelFactory
import com.bitcodetech.practicemvvmretrofit.network.UserApiService
import com.bitcodetech.practicemvvmretrofit.repository.UserRepository
import com.bitcodetech.practicemvvmretrofit.viewmodel.UserViewModel

class UserFragment : Fragment() {
private lateinit var binding: UserFragmentBinding
private lateinit var userViewModel: UserViewModel
private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initObserver()
        initAdapter()
        initListeners()

        userViewModel.fetchUsers()
        return binding.root
    }
    private fun initListeners() {
        binding.recyclerUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        userViewModel.fetchUsers()
                    }
                }
            })
    }
    private fun initAdapter(){
        userAdapter = UserAdapter(userViewModel.users)
        binding.recyclerUsers.adapter = userAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        userViewModel.userMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                userAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initViewModels(){
        userViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                UserRepository(
                    UserApiService.getInstance()
                )
            )
        )[UserViewModel::class.java]
    }
    private fun initViews(){
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }
}