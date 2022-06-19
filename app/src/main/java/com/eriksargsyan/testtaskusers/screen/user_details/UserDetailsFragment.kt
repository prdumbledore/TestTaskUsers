package com.eriksargsyan.testtaskusers.screen.user_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Locale

import com.eriksargsyan.testtaskusers.R
import com.eriksargsyan.testtaskusers.screen.base.BaseFragment
import com.eriksargsyan.testtaskusers.databinding.FragmentUserDetailsBinding
import com.eriksargsyan.testtaskusers.model.data.domain.User
import com.eriksargsyan.testtaskusers.screen.user_list.UserAdapter


class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>(
    { inflater, container ->
        FragmentUserDetailsBinding.inflate(inflater, container, false)
    }
) {

    private val args: UserDetailsFragmentArgs by navArgs()
    private var userId: Int = -1

    private val viewModel: UserDetailsViewModel by activityViewModels()
    private val friendsAdapter: UserAdapter by lazy {
        UserAdapter { user ->
            if (user.isActive) {
                findNavController().navigate(
                    UserDetailsFragmentDirections.actionUserDetailsSelf(user = user.id)
                )
            } else {
                showMessage(R.string.user_offline)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userId = args.user
        with(binding) {
            recyclerUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = friendsAdapter
            }
            emailAddress.setOnClickListener {
                startActivity(buildEmailIntent(emailAddress.text))
            }
            phoneNumber.setOnClickListener {
                startActivity(buildPhoneIntent(phoneNumber.text))
            }
            latitudeLongitude.setOnClickListener {
                startActivity(buildGeoIntent(latitudeLongitude.text))
            }
        }

        viewModel.fetchUserWithHisFriends(userId)

        viewLifecycleOwner.lifecycleScope.launch  {
            viewModel.state.collect { state ->
                when (state) {
                    is UserWithFriendsState.Loading -> {}
                    is UserWithFriendsState.Success -> {
                        fillField(state.user)
                        friendsAdapter.submitList(state.friends)
                    }
                }
            }
        }
    }

    private fun fillField(user: User) {
        binding.apply {
            imageGender.setImageResource(user.gender.id)
            name.text = user.name
            age.text = getString(R.string.age, user.age)
            company.text = getString(R.string.company, user.company)
            emailAddress.text = user.email
            phoneNumber.text = user.phone
            address.text = getString(R.string.address, user.address)
            eyeColor.setImageResource(user.eyeColor.id)
            favoriteFruit.setImageResource(user.favoriteFruit.id)
            registered.text = getString(R.string.registered, formatDate(user.registered))
            latitudeLongitude.text = "${user.latitude}, ${user.longitude}"
            about.text = user.about
        }
    }

    private fun buildEmailIntent(address: CharSequence) = Intent(Intent.ACTION_SENDTO).apply {
        val mailto = "mailto:" + arrayOf(address).joinToString(",")
        data = Uri.parse(mailto)
    }

    private fun buildPhoneIntent(phoneNumber: CharSequence) = Intent(Intent.ACTION_DIAL).apply {
        val phoneCall = "tel:" + Regex("""[-()\s]""").replace(phoneNumber, "")
        data = Uri.parse(phoneCall)
    }

    private fun buildGeoIntent(geo: CharSequence) = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:$geo")
    }

    companion object {
        private val UTC_TIME_ZONE = TimeZone.getTimeZone("UTC")
        private val FORMAT_FROM by lazy {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX", Locale.ENGLISH).apply {
                timeZone = UTC_TIME_ZONE
            }
        }
        private val FORMAT_TO by lazy {
            SimpleDateFormat("HH:mm dd.MM.yy", Locale.ENGLISH).apply {
                timeZone = UTC_TIME_ZONE
            }
        }

        fun formatDate(fromDate: String): String {
            return FORMAT_TO.format(FORMAT_FROM.parse(fromDate)!!)
        }
    }

}