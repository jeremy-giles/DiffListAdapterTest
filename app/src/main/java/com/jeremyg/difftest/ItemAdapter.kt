package com.jeremyg.difftest

import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.recycler_item.view.*
import timber.log.Timber


class ItemAdapter(
        var listener: ListAdapterListener
) : DataBindingAdapter<Item>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.recycler_item

    override fun onBindViewHolder(holder: DataBindingViewHolder<Item>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.tv_position.text = "Pos: $position"

        holder.itemView.setOnLongClickListener {
            Timber.d("List item count: ${itemCount}, position: $position")
            listener.onLongViewClick(getItem(position), position)
        }
    }

    interface ListAdapterListener {
        fun onLongViewClick(item: Item, position: Int) : Boolean
    }
}
