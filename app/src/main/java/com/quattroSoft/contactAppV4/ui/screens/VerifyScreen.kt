package com.quattroSoft.contactAppV4.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quattroSoft.contactAppV4.R
import com.quattroSoft.contactAppV4.data.model.request.VerifyModel
import com.quattroSoft.contactAppV4.databinding.ScreenVerifyBinding
import com.quattroSoft.contactAppV4.presenter.VerifyViewModel
import com.quattroSoft.contactAppV4.presenter.impl.VerifyViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyScreen : Fragment(R.layout.screen_verify) {
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel : VerifyViewModel by viewModels<VerifyViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val args : VerifyScreenArgs by navArgs()
    var message = ""
    var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.verifyLiveData.observe(this , verifyObserver)
//        viewModel.onErrorLiveData.observe(this, onErrorObserver)
        var str = args.massage
        message = str.subSequence(1 , args.massage.length-1).toString()
        var str1 = args.number
        number = str1.subSequence(1 , args.number.length-1).toString()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.phone.setText(number)
        viewModel.loadingLiveData.observe(viewLifecycleOwner , loadingObserver)


        if(message != "null")  Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()



        binding.save.setOnClickListener {
            binding.apply {
                var code = code.text.toString()
                var num = phone.text.toString()
                viewModel.verifyData(VerifyModel(num , code))
            }

        }




    }

    private val verifyObserver = Observer<Unit>{
        navController.navigate(R.id.action_verifyScreen_to_dashboardScreen)
    }
    private val onErrorObserver = Observer<String>{
        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
    }
    private val loadingObserver = Observer<Boolean>{
        if (it) binding.progress.visibility = View.VISIBLE
        if (!it) binding.progress.visibility = View.GONE
    }


}