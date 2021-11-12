package com.example.ilanguage.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ilanguage.R
import com.example.ilanguage.models_login.User

class TeacherAdapter(val teachers: List<User>, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<TeacherPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_teacher,parent,false)
        return TeacherPrototype(view)
    }
    interface OnItemClickListener {
        fun onItemClicked(user: User)
    }

    override fun onBindViewHolder(holder: TeacherPrototype, position: Int) {
        val teacher=teachers[position]
        holder.bind(teachers[position])
        holder.cvTeacherSearch.setOnClickListener {
            itemClickListener.onItemClicked(teacher)
        }
    }

    override fun getItemCount(): Int {
       return teachers.size
    }
}

class TeacherPrototype(itemView: View) :RecyclerView.ViewHolder(itemView){
    val tvTeacherName = itemView.findViewById<TextView>(R.id.tvTeacherName)
    val tvTeacherDescription = itemView.findViewById<TextView>(R.id.tvTeacherDescription)
    fun bind(teacher : User){
       tvTeacherName.text = teacher.name
        tvTeacherDescription.text = teacher.description
    }
    val cvTeacherSearch= itemView.findViewById<CardView>(R.id.cvTeacherSearch)

}
