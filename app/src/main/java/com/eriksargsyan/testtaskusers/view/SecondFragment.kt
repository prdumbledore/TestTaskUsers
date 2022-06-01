package com.eriksargsyan.testtaskusers.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.eriksargsyan.testtaskusers.R
import com.eriksargsyan.testtaskusers.databinding.FragmentSecondBinding
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.viewmodel.SecondFragmentViewModel
import java.text.SimpleDateFormat
import java.util.*


class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()
    private var userId: Int = -1

    private val secondFragmentVM: SecondFragmentViewModel by activityViewModels()

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = args.user
        secondFragmentVM.createFriendsListForCurrentUser(userId)
        secondFragmentVM.liveData.observe(viewLifecycleOwner) { resource ->
            binding.recyclerUserList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = UserAdapter(resource.subList(1, resource.size), false)
            }
            fillField(resource!![0])
        }

        binding.emailAddress.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                val mailto = "mailto:" + arrayOf(binding.emailAddress.text).joinToString(",")
                data = Uri.parse(mailto)
            }
            startActivity(Intent.createChooser(intent, null))
        }

        binding.phoneNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                val phoneNum = "tel:" + Regex("""[-()\s]""").replace(binding.phoneNumber.text, "")
                data = Uri.parse(phoneNum)
            }
            startActivity(Intent.createChooser(intent, null))
        }

        binding.latitudeLongitude.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                val geo = binding.latitudeLongitude.text
                data = Uri.parse("geo:$geo")
            }
            startActivity(Intent.createChooser(intent, null))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillField(user: User) {
        binding.apply {
            if (user.gender == "female")
                imageGender.setImageResource(R.drawable.female)
            else imageGender.setImageResource(R.drawable.male)
            name.text = user.name
            age.text = getString(R.string.age, user.age)
            company.text = getString(R.string.company, user.company)
            emailAddress.text = user.email
            phoneNumber.text = user.phone
            address.text = getString(R.string.address, user.address)
            eyeColor.setImageResource(user.eyeColor.id)
            favoriteFruit.setImageResource(user.favoriteFruit.id)
            registered.text = getString(R.string.address, formatDate(user.registered))
            latitudeLongitude.text = "${user.latitude}, ${user.longitude}"
            about.text = user.about
        }
    }

    companion object {
        fun formatDate(fromDate: String): String {
            val sdfF = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX", Locale.ENGLISH)
            val sdfT = SimpleDateFormat("HH:mm dd.MM.yy", Locale.ENGLISH)

            sdfF.timeZone = TimeZone.getTimeZone("UTC")
            sdfT.timeZone = TimeZone.getTimeZone("UTC")

            val date = sdfF.parse(fromDate)

            return sdfT.format(date!!)
        }
    }

}