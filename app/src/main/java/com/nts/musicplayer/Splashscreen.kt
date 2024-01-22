package com.nts.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()

        val handler = Handler()
        Handler().postDelayed ( {
          val intent = Intent (this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)



    }
}