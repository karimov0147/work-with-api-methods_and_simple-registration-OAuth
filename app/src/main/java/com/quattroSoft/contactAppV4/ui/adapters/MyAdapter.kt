package com.quattroSoft.contactAppV4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.databinding.ItemBinding

class MyAdapter : ListAdapter<ContactModel ,MyAdapter.ViewHolder>(DiffUtilCallback) {

    var listener : ((ContactModel)-> Unit)? = null
    var deletelistener : ((Int)-> Unit)? = null

    object DiffUtilCallback : DiffUtil.ItemCallback<ContactModel>() {
        override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem.firstName == newItem.firstName && oldItem.lastName == newItem.lastName && oldItem.phone == newItem.phone
        }
    }

    inner class ViewHolder(var view : ItemBinding) : RecyclerView.ViewHolder(view.root) {
        init {
//            var item = getItem(bindingAdapterPosition)
//            view.imageFilterView.setOnClickListener {
//                listener?.invoke(item)
//            }
        }
        fun bind(){
            var item = getItem(bindingAdapterPosition)
            view.apply {
                name.text = item.firstName
                number.text = item.phone
            }


            view.deleteBtn.setOnClickListener{deletelistener?.invoke(item.id)}

            view.editBtn.setOnClickListener{listener?.invoke(item)}





        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(ItemBinding.inflate(
        LayoutInflater.from(parent.context),  parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun initListener(l :(ContactModel) -> Unit){
        listener = l


    } fun initDeleteListener(l :(Int) -> Unit){
        deletelistener = l
    }

    fun deleteItem(position : Int){
        notifyItemRemoved(position)
    }

}