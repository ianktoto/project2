package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class UserListAdapter(private val list: List<User>) : RecyclerView.Adapter<UserListAdapter.MyVH>() {
    class MyVH(val view: View) : RecyclerView.ViewHolder(view) {
        val login : TextView = view.findViewById(R.id.login)
        val last_seen : TextView = view.findViewById(R.id.last_seen)
        val region : TextView = view.findViewById(R.id.region)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val layout = when(viewType) {
            USER_DEFAULT -> R.layout.default_user_card
            USER_ADMIN -> R.layout.admin_user_card
            else -> Log.d("New data", "Unknown user type found!")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MyVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val elem = list[position]
        holder.login.text = elem.login
        holder.last_seen.text = elem.last_seen
        holder.region.text = elem.region
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position].type) {
            0 -> USER_DEFAULT
            1 -> USER_ADMIN
            else -> USER_OTHER
        }
    }

    companion object {
        private const val USER_DEFAULT = 0
        private const val USER_ADMIN = 1
        private const val USER_OTHER = 2
    }
}