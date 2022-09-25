package com.quattroSoft.contactAppV4.ui.screens


import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quattroSoft.contactAppV4.R
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.request.ModelForAdding
import com.quattroSoft.contactAppV4.data.model.request.ModelForEditing
import com.quattroSoft.contactAppV4.databinding.CustomDialogBinding
import com.quattroSoft.contactAppV4.databinding.CustomViewBinding
import com.quattroSoft.contactAppV4.databinding.ScreenDashboardBinding
import com.quattroSoft.contactAppV4.presenter.DashboardViewModel
import com.quattroSoft.contactAppV4.presenter.impl.DashboardViewModelImpl
import com.quattroSoft.contactAppV4.ui.adapters.MyAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardScreen : Fragment(R.layout.screen_dashboard) {
    private val binding by viewBinding(ScreenDashboardBinding::bind)
//    private val customBind by viewBinding(CustomViewBinding::bind)
    private val viewModel : DashboardViewModel by viewModels<DashboardViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter = MyAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        viewModel.loadContactsLiveData.observe(this , loadDataObserver)

        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater: MenuInflater = MenuInflater(requireContext())
            inflater.inflate(R.menu.main_menu, menu)
            return true
        }





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.loadingLiveData.observe(viewLifecycleOwner , loadingObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner , errorObserver)



        viewModel.getAllContacts()

        binding.toolbar.apply {
            inflateMenu(R.menu.main_menu)
        }


        binding.toolbar.setOnMenuItemClickListener(
            object : androidx.appcompat.widget.Toolbar.OnMenuItemClickListener{
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Toast.makeText(requireContext(), "dsadsasda", Toast.LENGTH_SHORT).show()
                    return false
                }

            }
        )



        adapter.initListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            val customView = View.inflate(requireContext() , R.layout.custom_view , null)
           val customBind = CustomViewBinding.bind(customView)
            dialogBuilder.setView(customView)
            customBind.apply {
                name.setText(it.firstName)
                lastName.setText((it.lastName))
                number.setText((it.phone))
            }

            var dialog = dialogBuilder.create()
            dialog.show()

            customBind.save.setOnClickListener {itt->
                var name = customBind.name.text.toString()
                var lastName = customBind.lastName.text.toString()
                var phone = customBind.number.text.toString()
                viewModel.editContact(ModelForEditing(it.id , name , lastName , phone))
                dialog.dismiss()
            }

        }

        adapter.initDeleteListener {
            var dialogBuilder = AlertDialog.Builder(requireContext())
            val customView = View.inflate(requireContext() , R.layout.custom_dialog , null)
            val customBind = CustomDialogBinding.bind(customView)
            dialogBuilder.setView(customView)

            var dialog = dialogBuilder.create()
            dialog.show()

            customBind.apply {
                yesBtn.setOnClickListener {itt->
                    viewModel.deleteContact(it)
                    dialog.dismiss()
                }
                noBtn.setOnClickListener {
                    dialog.dismiss()
                }
            }




        }

        binding.fab.setOnClickListener{
            var dialogBuilder = AlertDialog.Builder(requireContext())
            val customView = View.inflate(requireContext() , R.layout.custom_view , null)
            val customBind = CustomViewBinding.bind(customView)
            dialogBuilder.setView(customView)

            var dialog = dialogBuilder.create()
            dialog.show()

            customBind.save.setOnClickListener {itt->
                var name = customBind.name.text.toString()
                var lastName = customBind.lastName.text.toString()
                var phone = customBind.number.text.toString()
                viewModel.addContact(ModelForAdding( name , lastName , phone))
                dialog.dismiss()
            }

        }
    }



    private var loadDataObserver = Observer<List<ContactModel>>{
        adapter.submitList(it)
    }

    private var loadingObserver = Observer<Boolean>{
        if (it) binding.progress.visibility = View.VISIBLE
        if (!it) binding.progress.visibility = View.GONE
    }
    private var errorObserver = Observer<Unit>{
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }
    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = MenuInflater(requireContext())
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }







}