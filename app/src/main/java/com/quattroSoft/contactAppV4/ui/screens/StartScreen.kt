package com.quattroSoft.contactAppV4.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quattroSoft.contactAppV4.R
import com.quattroSoft.contactAppV4.data.source.local.LocalStorage
import com.quattroSoft.contactAppV4.databinding.ScreenStartBinding
import com.quattroSoft.contactAppV4.presenter.StartViewModel
import com.quattroSoft.contactAppV4.presenter.impl.StartViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class StartScreen : Fragment(R.layout.screen_start) {

    private val binding by viewBinding(ScreenStartBinding::bind)
    private val viewModel : StartViewModel by viewModels<StartViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
   // private val adapter by lazy { ArticlesAdapter() }
   // private val args : ArticleScreenArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateToDashboard.observe(this , toDashboardObserver)
        viewModel.navigateToAuth.observe(this , toAuthObserver)
    }





    private val toDashboardObserver = Observer<Unit>{
        navController.navigate(R.id.action_startScreen_to_dashboardScreen)
    }

    private val toAuthObserver = Observer<Unit>{
        navController.navigate(R.id.action_startScreen_to_authScreen)
    }




}