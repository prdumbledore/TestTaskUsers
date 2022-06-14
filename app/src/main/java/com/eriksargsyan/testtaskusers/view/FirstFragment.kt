package com.eriksargsyan.testtaskusers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eriksargsyan.testtaskusers.databinding.FragmentFirstBinding
import com.eriksargsyan.testtaskusers.viewmodel.FirstFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val firstFragmentVM: FirstFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        firstFragmentVM.checkDatabaseAfterStart(requireContext())

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstFragmentVM.liveData.observe(viewLifecycleOwner) {
            binding.recyclerUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = UserAdapter(it, true)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            firstFragmentVM.swipeRefresh(requireContext(), view)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}