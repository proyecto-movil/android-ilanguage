package com.example.ilanguage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ilanguage.R
import com.example.ilanguage.models_login.Session

class SessionAdapter(val sessions: List<Session>): RecyclerView.Adapter<SessionPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionPrototype {
        val view =  LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_session,parent,false)
        return SessionPrototype(view)
    }

    override fun onBindViewHolder(holder: SessionPrototype, position: Int) {
        holder.bind(sessions[position])
    }

    override fun getItemCount(): Int {
        return sessions.size
    }

}

class SessionPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTutorName = itemView.findViewById<TextView>(R.id.tvTutorName)
    val tvInformation = itemView.findViewById<TextView>(R.id.tvInformation)
    val tvTopic = itemView.findViewById<TextView>(R.id.tvTopicLanguage)
    val tvDateEnd = itemView.findViewById<TextView>(R.id.tvDateEnd)
    fun bind(session : Session){
        tvTutorName.text = "Tutor"
        tvInformation.text = session.information
        tvTopic.text = session.topic
        tvDateEnd.text = session.endAt.toString()
    }
}
