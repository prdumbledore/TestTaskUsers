package com.eriksargsyan.testtaskusers.screen.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.eriksargsyan.testtaskusers.R
import com.eriksargsyan.testtaskusers.databinding.ItemLayoutUserBinding
import com.eriksargsyan.testtaskusers.model.data.domain.User

class UserAdapter(
    private val onCardClickListener: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list: MutableList<User> = mutableListOf()

    fun submitList(newList: List<User>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(
        private val binding: ItemLayoutUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listItem: User) {
            with(binding) {
                name.text = listItem.name
                email.text = listItem.email
                imageActive.setImageResource(
                    if (listItem.isActive) R.drawable.circle_active
                    else R.drawable.circle_not_active
                )
                cardUser.setOnClickListener {
                    onCardClickListener.invoke(listItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemViewBinding = ItemLayoutUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }
}