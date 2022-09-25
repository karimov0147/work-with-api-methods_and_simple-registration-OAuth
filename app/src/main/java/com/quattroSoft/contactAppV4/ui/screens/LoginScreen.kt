package com.quattroSoft.contactAppV4.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quattroSoft.contactAppV4.R
import com.quattroSoft.contactAppV4.data.model.request.LoginModel
import com.quattroSoft.contactAppV4.data.model.response.Message
import com.quattroSoft.contactAppV4.databinding.ScreenAuthBinding
import com.quattroSoft.contactAppV4.databinding.ScreenLoginBinding
import com.quattroSoft.contactAppV4.presenter.AuthViewModel
import com.quattroSoft.contactAppV4.presenter.LoginViewModel
import com.quattroSoft.contactAppV4.presenter.impl.AuthViewModelImpl
import com.quattroSoft.contactAppV4.presenter.impl.LoginViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel : LoginViewModel by viewModels<LoginViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loginLiveData.observe(this , loginPressedObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.loadingLiveData.observe(viewLifecycleOwner , loadingObserver )
        viewModel.messageLiveData.observe(viewLifecycleOwner , messageObserver)

        binding.apply {


            save.setOnClickListener {
                val phone = phone.text.toString()
                val pass = password.text.toString()
                viewModel.onLoginPressed(LoginModel(phone , pass))
            }

        }





    }
    private var loginPressedObserver = Observer<Unit>{
        navController.navigate(R.id.action_loginScreen_to_dashboardScreen)
    }
    private var loadingObserver = Observer<Boolean>{
        if (it) binding.progress.visibility = View.VISIBLE
        if (!it) binding.progress.visibility = View.GONE
    }

    private var messageObserver = Observer<Message>{
        Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
    }




}