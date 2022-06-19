package com.eriksargsyan.testtaskusers.screen.user_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import com.eriksargsyan.testtaskusers.BuildConfig
import com.eriksargsyan.testtaskusers.R
import com.eriksargsyan.testtaskusers.screen.base.BaseFragment
import com.eriksargsyan.testtaskusers.databinding.FragmentUserListBinding
import com.eriksargsyan.testtaskusers.model.network.SettingsStorage

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>(
    { inflater, container ->
        FragmentUserListBinding.inflate(inflater, container, false)
    }
) {

    @Inject
    lateinit var settingsStorage: SettingsStorage

    private val viewModel: UserListViewModel by activityViewModels()

    private val userAdapter: UserAdapter by lazy {
        UserAdapter { user ->
            if (user.isActive) {
                findNavController().navigate(
                    UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(
                        user = user.id
                    )
                )
            } else {
                showMessage(R.string.user_offline)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsStorage.saveToken(BuildConfig.TOKEN)
        with(binding) {
            recyclerUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                viewModel.fetchUsers()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { userState ->
                when (userState) {
                    is UserState.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }
                    is UserState.Success -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        userAdapter.submitList(userState.users)
                    }
                    is UserState.Error -> {
                        showMessage(R.string.no_net_connection)
                    }
                }
            }
        }
    }
}