package com.quattroSoft.contactAppV4.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quattroSoft.contactAppV4.R
import com.quattroSoft.contactAppV4.data.model.request.RegisterRequestModel
import com.quattroSoft.contactAppV4.databinding.ScreenAuthBinding
import com.quattroSoft.contactAppV4.databinding.ScreenStartBinding
import com.quattroSoft.contactAppV4.presenter.AuthViewModel
import com.quattroSoft.contactAppV4.presenter.StartViewModel
import com.quattroSoft.contactAppV4.presenter.impl.AuthViewModelImpl
import com.quattroSoft.contactAppV4.presenter.impl.StartViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {

    private val binding by viewBinding(ScreenAuthBinding::bind)
    private val viewModel : AuthViewModel by viewModels<AuthViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.toLoginLiveData.observe(this , toLoginObserver)
        viewModel.toVerifyLiveData.observe(this , toVerifyObserver)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingLiveData.observe(viewLifecycleOwner , loadingObserver)
        Timber.tag("salom").d("SALOM")
        binding.toLoginBtn.setOnClickListener { viewModel.toLoginScreen() }

        binding.save.setOnClickListener {
            binding.apply {
                val name = firstName.text.toString()
                val lastname = lastName.text.toString()
                val phone = phone.text.toString()
                val pass = password.text.toString()
                val user = RegisterRequestModel(name , lastname , phone , pass)
                viewModel.toVerifyScreen(user)
            }
        }
    }

    private var toLoginObserver = Observer<Unit>{
        navController.navigate(R.id.action_authScreen_to_loginScreen)
    }

    private var toVerifyObserver = Observer<Map<String , String>> {
     //   Toast.makeText(requireContext(), it.values.toString(), Toast.LENGTH_SHORT).show()
        navController.navigate(AuthScreenDirections.actionAuthScreenToVerifyScreen(it.keys.toString(),it.values.toString() ))

    }

    private var loadingObserver = Observer<Boolean>{
        if (it) binding.progress.visibility = View.VISIBLE
        if (!it) binding.progress.visibility = View.GONE
    }



}