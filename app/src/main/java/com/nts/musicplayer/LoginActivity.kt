package com.nts.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var signupbtn: Button
    lateinit var editTextLoginEmail: TextInputEditText
    lateinit var editTextLoginPassword: TextInputEditText
    lateinit var buttonSignIn: Button
    lateinit var btn_ForgotPassword: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        signupbtn = findViewById(R.id.buttonSignUP)
        editTextLoginEmail = findViewById(R.id.userLoginEmail)
        editTextLoginPassword = findViewById(R.id.userLoginPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
        btn_ForgotPassword = findViewById(R.id.btn_ForgotPassword)

        signupbtn.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        buttonSignIn.setOnClickListener {

            val userEmail = editTextLoginEmail.text.toString()
            val userPassword = editTextLoginPassword.text.toString()

            signInWithFirebase(userEmail, userPassword)
        }

        btn_ForgotPassword.setOnClickListener {

            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }


    fun signInWithFirebase(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, SongsListActivity::class.java)
                startActivity(intent)

            } else {

                Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //User Remembering Functionality
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null){
            val intent = Intent(this, SongsListActivity::class.java)
            startActivity(intent)
        }
    }



}