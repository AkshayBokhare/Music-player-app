package com.nts.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var forgetbtn: Button
    lateinit var editTextForgetPasswordEmail: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        supportActionBar?.hide()

        forgetbtn = findViewById(R.id.resetpassword)
        editTextForgetPasswordEmail = findViewById(R.id.editTextForgetPasswordEmail)

        forgetbtn.setOnClickListener {

            val email = editTextForgetPasswordEmail.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Toast.makeText(
                        this,
                        "We sent a password reset mail to your mail address",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
                }


            }
        }


    }
}