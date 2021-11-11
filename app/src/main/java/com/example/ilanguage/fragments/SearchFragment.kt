package com.example.ilanguage.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import com.example.ilanguage.R
import com.example.ilanguage.controllers_login.RetrofitLanguage
import com.example.ilanguage.controllers_login.RetrofitTopic
import com.example.ilanguage.models_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    var languageOptions: List<Language> = emptyList()
    var topicOptions: List<Topic> = emptyList()
    var teachersOptions: List<User> = emptyList()
    var languageString = mutableListOf<String>()
    var topicString = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btSearchTutor = view.findViewById<ImageButton>(R.id.btSearchTutor)
        btSearchTutor.setOnClickListener {
           getTeachers()
        }
        getLanguages()
        getTopics(requireArguments().getInt("userId"))
    }

    private fun getTeachers() {
        var languagePicker = requireView().findViewById<AutoCompleteTextView>(R.id.autoLanguage)
        var topicPicker = requireView().findViewById<AutoCompleteTextView>(R.id.autoTopic)
        languageOptions.find {
            when(it.name){
                languagePicker.text.toString() ->{
                   Log.e("Language",it.id.toString())
                   true
                }
                else->false
            }
        }
        topicOptions.find{
            when(it.name){
               topicPicker.text.toString() ->{
                   Log.e("topic",it.id.toString())
                   true
               }
                else -> false
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
    private fun getLanguages(){
        if (languageString.size > 0){
           languageString.clear()
            languageOptions = emptyList()
        }
       val request = RetrofitLanguage.service.getLanguages()
       request.enqueue(object : Callback<LanguageContent>{
           override fun onResponse(
               call: Call<LanguageContent>,
               response: Response<LanguageContent>
           ) {
               languageOptions = response.body()!!.languages
               for (language in languageOptions)
               {
                   languageString.add(language.name)
               }
               val languageAutoComplete = view!!.findViewById<AutoCompleteTextView>(R.id.autoLanguage)
               val adapter = ArrayAdapter(context!!, R.layout.option_language, languageString)
               languageAutoComplete.setText(adapter.getItem(0),false)
               languageAutoComplete.setAdapter(adapter)
           }

           override fun onFailure(call: Call<LanguageContent>, t: Throwable) {
               Log.d("Error al obtener lenaguages en search",t.toString())
           }

       })
    }
    private fun getTopics(userId : Int){
        if(topicString.size > 0){
           topicString.clear()
            topicOptions = emptyList()
        }

       val request = RetrofitTopic.service.getTopics(userId)
        request.enqueue(object : Callback<TopicContent>{
            override fun onResponse(call: Call<TopicContent>, response: Response<TopicContent>) {
                topicOptions = response.body()!!.topics
                for(topic in topicOptions)
                {
                   topicString.add(topic.name)
                }
                val topicAutoComplete = view!!.findViewById<AutoCompleteTextView>(R.id.autoTopic)
                val adapter = ArrayAdapter(context!!,R.layout.option_topic,topicString)
                topicAutoComplete.setText(adapter.getItem(0),false)
                topicAutoComplete.setAdapter(adapter)

            }

            override fun onFailure(call: Call<TopicContent>, t: Throwable) {
                Log.d("Error al obtener los topicos en search por id de usuario",t.toString())
            }

        })
    }
}