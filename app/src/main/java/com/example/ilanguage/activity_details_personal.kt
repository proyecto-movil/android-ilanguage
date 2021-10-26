package com.example.ilanguage

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.ilanguage.controllers_login.RetrofitLanguage
import com.example.ilanguage.controllers_login.RetrofitLanguageUser
import com.example.ilanguage.controllers_login.RetrofitUser
import com.example.ilanguage.models_login.*
import com.example.ilanguage.services_login.LanguageService
import com.example.ilanguage.services_login.UserService
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.reflect.Type
import kotlin.math.log

class activity_details_personal : AppCompatActivity() {
    var languageOptions: List<Language> = emptyList()
    var userLogged: User? = null
    var optionUser: Int = 0
    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_personal)

        var languageString = mutableListOf<String>()

        setLanguageListString(languageString)

        continueUserInformation()
        getBackActivityUserInformation()

        var  extras : Bundle? = intent.extras;

        getOptionUserSelected(extras)
        changeTextValueUserOption(optionUser)
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        loadUserLogged()

    }
    private fun getOptionUserSelected(extras: Bundle?) {
        if (extras != null) {
            optionUser = extras.getInt("optionUser")
        }
    }
    private fun changeTextValueUserOption(optionUser: Int) {
        val textUserOption = findViewById<TextView>(R.id.textViewUser)
        if(optionUser == 1){
            textUserOption.text = "Create an Account: Teacher"
        }
        if(optionUser == 2){
            textUserOption.text = "Create an Account: Student"
        }
        if(optionUser == 0){
            textUserOption.text = "Create an Account: ERROR"
        }
    }
    private fun saveDataUserLogged() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var json : String = Gson().toJson(userLogged);
        editor.putString("userLogged",json)
        editor.apply();
    }

    private fun loadUserLogged() {
        var json : String? = sharedPreferences.getString("userLogged",null)
        var userType: Type = object : TypeToken<User>() {}.type;
        userLogged = Gson().fromJson(json,userType)
        Log.e("USEEEEERRRRR", userLogged.toString())
    }

    private fun getBackActivityUserInformation(){
        val btnBack = findViewById<ImageView>(R.id.back_image3)
        btnBack.setOnClickListener {
            val intent = Intent(this,details_signup::class.java)
            startActivity(intent)
        }
    }


    private fun continueUserInformation(){
        val btnContinueUser = findViewById<Button>(R.id.btn_continue_signup_users)

        val description = findViewById<TextInputEditText>(R.id.descriptionInput)
        val birthdate = findViewById<TextInputEditText>(R.id.birthdateInput)
        val country = findViewById<TextInputEditText>(R.id.countryInput)
        val languagePicker = findViewById<AutoCompleteTextView>(R.id.languageAutoComplete)

        btnContinueUser.setOnClickListener {
            if(verifyAllDataComplete(description,birthdate,country,languagePicker)){

                RetrofitUser.service.createUser(userLogged!!).enqueue(
                    object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            userLogged = response?.body()

                            RetrofitLanguageUser.service.assignLanguageToUserId(userLogged!!.id,getIdLanguageSelected(languagePicker.text.toString())).enqueue(
                                object : Callback<User>
                                {
                                    override fun onResponse( call: Call<User>,response: Response<User>) {
                                        var userReponse = response?.body()
                                        Log.e("API LANGUAGE ASSIGNED",Gson().toJson(userLogged))

                                        RetrofitUser.service.assignUserRole(userLogged!!.id,optionUser).enqueue(
                                            object : Callback<User> {
                                                override fun onResponse( call: Call<User>,  response: Response<User>) {
                                                    var userResponse = response?.body()
                                                    Log.e("API ROLLED ASSIGNED",Gson().toJson(userResponse))

                                                    //NOS VAMOS
                                                    saveDataUserLogged()
                                                    val intent = Intent(applicationContext,MainMenuActivity::class.java)
                                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                                    startActivity(intent)
                                                }

                                                override fun onFailure(call: Call<User>, t: Throwable) {
                                                    Log.e("bad bad ","SASSIGNING ROLE RESPOND BAD")
                                                }
                                            })
                                    }
                                    override fun onFailure(call: Call<User>, t: Throwable) {
                                        t.printStackTrace()
                                        Log.e("bad bad ","SASSIGNING LANGUAGE RESPOND BAD")

                                    }
                                })
                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            t.printStackTrace()
                            Log.e("API RESPONSE BAD BAD",t.message.toString())
                        }
                    }
                )
            } else {
                Toast.makeText(
                    this,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    private fun getIdLanguageSelected(languageSelected : String): Int {
        for(language in languageOptions) {
            if (language.name == languageSelected) {
                return language.id
            }
        }
        return 0
    }

    private fun verifyAllDataComplete(description: TextInputEditText?, birthdate: TextInputEditText?, country: TextInputEditText?, languagePicker: AutoCompleteTextView?): Boolean {
        if(TextUtils.isEmpty(description?.text.toString()) || TextUtils.isEmpty((birthdate?.text.toString()))  ) {
            return false
        }
        if(TextUtils.isEmpty(country?.text.toString()) || TextUtils.isEmpty((languagePicker?.text.toString()))  ) {
            return false
        }
        //TODO: Complete this
        userLogged!!.description = description?.text.toString()
        return true
    }

    private fun goToMainPrincipalView() {
        saveDataUserLogged()
        val intent = Intent(this,MainMenuActivity::class.java)
        startActivity(intent)
    }

    private fun setLanguageListString(languageString: MutableList<String>) {
        //val request = LanguageController().languageService.getLanguages()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        RetrofitLanguage.service.getLanguages().enqueue(object : Callback<LanguageContent> {
            override fun onResponse(call: Call<LanguageContent>?, response: Response<LanguageContent>?) {
                val apiResponse = response?.body()
                if (apiResponse != null) {
                    UpdateListLanguage(apiResponse.languages)
                    for (language in languageOptions)
                    {
                        languageString.add(language.name)
                    }
                    val languageAutoComplete = findViewById<AutoCompleteTextView>(R.id.languageAutoComplete)

                    val adapter = ArrayAdapter(this@activity_details_personal, R.layout.option_language, languageString)
                    languageAutoComplete.setText(adapter.getItem(0),false);
                    languageAutoComplete.setAdapter(adapter)
                }
            }
            override fun onFailure(call: Call<LanguageContent>, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }


    private fun UpdateListLanguage(languages: List<Language>) {
        languageOptions = languages
    }
}