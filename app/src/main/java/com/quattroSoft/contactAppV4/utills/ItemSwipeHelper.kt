package com.quattroSoft.contactAppV4.utills

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.ui.adapters.MyAdapter

class ItemSwipeHelper(val adapter : MyAdapter) : ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var position = viewHolder.bindingAdapterPosition
        adapter.deleteItem(position)
    }
}