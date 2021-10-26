package com.example.ilanguage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ilanguage.models_login.User
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class details_signup : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    private var optionUser: Int = 0
    private var userRegistration : User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_signup)
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        var extras: Bundle? = intent.extras;
        getOptionUserSelected(extras)

        val nameInput = findViewById<TextInputEditText>(R.id.nameInputText)
        val lastNameInput = findViewById<TextInputEditText>(R.id.lastNameInputText)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInputText)
        val passwordConfirmInput = findViewById<TextInputEditText>(R.id.passwordConfirmInputText)

        changeTextValueUserOption(optionUser)


        clickContinueRegistration(nameInput, lastNameInput, emailInput, passwordInput, passwordConfirmInput)
        getBackActivity()
    }

    private fun verifyTextInputCompleted(
        nameInput: TextInputEditText?,
        lastNameInput: TextInputEditText?,
        emailInput: TextInputEditText?,
        passwordInput: TextInputEditText?,
        passwordConfirmInput: TextInputEditText?
    ): Boolean {

        if (TextUtils.isEmpty(nameInput?.text.toString()) || TextUtils.isEmpty(lastNameInput?.text.toString())) {
            return false;
        }
        if (TextUtils.isEmpty(emailInput?.text.toString()) || TextUtils.isEmpty(passwordInput?.text.toString())) {
            return false;
        }
        if (TextUtils.isEmpty(passwordConfirmInput?.text.toString())) {
            return false;
        }
        return true;

    }

    private fun clickContinueRegistration( nameInput: TextInputEditText?, lastNameInput: TextInputEditText?, emailInput: TextInputEditText?,
        passwordInput: TextInputEditText?, passwordConfirmInput: TextInputEditText?    ) {

        val btnContinue = findViewById<Button>(R.id.btn_continue_signup)
        btnContinue.setOnClickListener {
            if (verifyTextInputCompleted( nameInput, lastNameInput, emailInput, passwordInput, passwordConfirmInput)) {

                var name: String = nameInput?.text.toString()
                var lastName: String = lastNameInput?.text.toString()
                var email: String = emailInput?.text.toString()
                var password: String = passwordInput?.text.toString()
                var passwordConfirmation: String = passwordConfirmInput?.text.toString()

                Log.e("CONTRA1",password)
                Log.e("CONTRA2",passwordConfirmation)

                if (password != passwordConfirmation) {
                    Toast.makeText(
                        this,
                        "Las contraseñas deben ser iguales",
                        Toast.LENGTH_LONG
                    ).show();
                } else {
                    ContinueRegistrationActivity(name, lastName, email, password)
                }

            } else {
                Toast.makeText(
                    this,
                    "Complete todos los datos requeridos",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }
    private fun ContinueRegistrationActivity(name: String, lastName: String, email: String, password: String) {

        userRegistration = User(0,name,lastName,email,password,"",0)

        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var json : String = Gson().toJson(userRegistration);
        editor.putString("userLogged",json)
        editor.apply();

        val intent = Intent(this,activity_details_personal::class.java)
        intent.putExtra("optionUser",optionUser)
        startActivity(intent)
    }

    private fun getBackActivity() {
        val btnBack = findViewById<ImageView>(R.id.back_image3)
        btnBack.setOnClickListener {
            val intent = Intent(this,select_signup::class.java)
            startActivity(intent)
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

    private fun getOptionUserSelected(extras: Bundle?) {
        if (extras != null) {
            optionUser = extras.getInt("optionRegister")
        }
    }

}

