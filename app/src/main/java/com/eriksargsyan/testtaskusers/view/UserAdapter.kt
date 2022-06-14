package com.eriksargsyan.testtaskusers.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eriksargsyan.testtaskusers.R
import com.eriksargsyan.testtaskusers.model.domain.User
import com.google.android.material.snackbar.Snackbar

class UserAdapter(private val userList: List<User>, private val actionType: Boolean) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: CardView = itemView.findViewById(R.id.card_user)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val email: TextView = itemView.findViewById(R.id.email)
        private val image: ImageView = itemView.findViewById(R.id.image_active)

        fun bind(listItem: User, actionType: Boolean) {
            name.text = listItem.name
            email.text = listItem.email
            if (listItem.isActive) image.setImageResource(R.drawable.is_active)
            else image.setImageResource(R.drawable.is_active1)
            card.setOnClickListener {
                if (listItem.isActive) {
                    val action = if (actionType)
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(user = listItem.id)
                    else SecondFragmentDirections.actionSecondFragmentSelf(user = listItem.id)
                        it.findNavController().navigate(action)
                } else {
                    Snackbar.make(itemView, "User is offline", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val listItem = userList[position]
        holder.bind(listItem, actionType)
    }

}