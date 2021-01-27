package saktidatt.myapplication.app

import saktidatt.myapplication.app.databinding.RecyclerItemBinding

data class AppViewHolder(val itemBinding: RecyclerItemBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemBinding.root)