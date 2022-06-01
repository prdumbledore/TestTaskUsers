package com.eriksargsyan.testtaskusers.view

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eriksargsyan.testtaskusers.databinding.FragmentFirstBinding
import com.eriksargsyan.testtaskusers.viewmodel.FirstFragmentViewModel
import com.eriksargsyan.testtaskusers.viewmodel.UserSharedPref
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val firstFragmentVM: FirstFragmentViewModel by activityViewModels()

    private lateinit var sharedPref: UserSharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        sharedPref = UserSharedPref(this.requireContext())

        if (!sharedPref.checkCacheDb()) {
            firstFragmentVM.getAllUsersFromNet()
            sharedPref.addUsersDb()
        } else {
            firstFragmentVM.getAllUsers()
        }

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstFragmentVM.liveData.observe(viewLifecycleOwner) { resource ->
            binding.recyclerUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = UserAdapter(resource, true)
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            val connectivityManager: ConnectivityManager? = requireActivity().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                firstFragmentVM.getAllUsersFromNet()
            } else {
               Snackbar.make(view, "No internet connection", Snackbar.LENGTH_SHORT).show()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}