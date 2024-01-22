package com.nts.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    //create firebase auth object
    val auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        val signupbtn = findViewById<Button>(R.id.buttonSignUpp)


        signupbtn.setOnClickListener {

            val userEmail =
                findViewById<TextInputEditText>(R.id.editTextSignUpEmail).text.toString()
            val userPassword =
                findViewById<TextInputEditText>(R.id.editTextSignUpPassword).text.toString()

            signupWithFireBase(userEmail, userPassword)

        }
    }

    fun signupWithFireBase(userEmail: String, userPassword: String) {

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(this, "Account Has been Created", Toast.LENGTH_SHORT).show()
                finish()

            } else {
                Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }

        }


    }
}