package com.example.ilanguage.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ilanguage.R
import com.example.ilanguage.adapters.SessionAdapter
import com.example.ilanguage.controllers_login.RetrofitSession
import com.example.ilanguage.models_login.Session
import com.example.ilanguage.models_login.SessionContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionFragment : Fragment() {

    lateinit var sessions: List<Session>
    lateinit var sessionAdapter: SessionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSession = requireView().findViewById<RecyclerView>(R.id.rvSessions)
        loadSession(rvSession)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SessionFragment()
    }

    private fun loadSession(rvSessions: RecyclerView) {
        val request = RetrofitSession.service.getSessionsByUserId(requireArguments().getInt("userId"))
        request.enqueue(object : Callback<SessionContent> {
            override fun onResponse(
                call: Call<SessionContent>,
                response: Response<SessionContent>
            ) {
                sessions = response.body()!!.sessions
                sessionAdapter = SessionAdapter(sessions)
                rvSessions.adapter = sessionAdapter
                rvSessions.layoutManager = LinearLayoutManager(context)
            }
            override fun onFailure(call: Call<SessionContent>, t: Throwable) {
                Log.d("Error de call", t.toString())
            }
        })
    }
}