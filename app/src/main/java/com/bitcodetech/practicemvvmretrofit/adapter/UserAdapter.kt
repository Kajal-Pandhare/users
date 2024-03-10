package com.bitcodetech.practicemvvmretrofit.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.practicemvvmretrofit.R
import com.bitcodetech.practicemvvmretrofit.models.User
import com.bumptech.glide.Glide

class UserAdapter(private val users : ArrayList<User>):
RecyclerView.Adapter<UserAdapter.UserViewModel>(){

    inner class UserViewModel(view: View): RecyclerView.ViewHolder(view){
        val image : ImageView
        val txtlogin : TextView
        val txtId : TextView
        val txtavatar : TextView



        init {
            image = view.findViewById(R.id.imgUser)
            txtlogin = view.findViewById(R.id.txtUserName)
            txtId = view.findViewById(R.id.txtid)
            txtavatar = view.findViewById(R.id.txt3)

        }
    }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view,null)

        return UserViewModel(view)
    }

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        val user = users[position]

       // holder.txtUsername.text = "${user.firsName} ${user.lastName}"
        holder.txtlogin.text = user.login
       holder.txtId.text = user.id
        holder.txtavatar.text = user.followers_url

        Glide.with(holder.itemView)
            .load(users[position].avatar_url)
            .circleCrop()
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.image)
    }
}