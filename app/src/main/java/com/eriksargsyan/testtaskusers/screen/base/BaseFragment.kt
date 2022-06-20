package com.eriksargsyan.testtaskusers.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.eriksargsyan.testtaskusers.model.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

open class BaseFragment<BINDING : ViewBinding>(
    val inflateFun: (
        inflater: LayoutInflater,
        container: ViewGroup?
    ) -> BINDING
) : Fragment() {

    private var _binding: BINDING? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateFun(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showMessage(@StringRes stringId: Int) {
       Snackbar.make(requireView(), stringId, Snackbar.LENGTH_SHORT).show()
    }
}