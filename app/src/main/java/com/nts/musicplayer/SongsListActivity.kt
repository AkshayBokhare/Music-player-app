package com.nts.musicplayer

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.nts.musicplayer.data.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongsListActivity : AppCompatActivity() {

    lateinit var myrecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_list)
        supportActionBar?.title= "Song List"

        myrecyclerView=findViewById(R.id.recyclerView)


        //Json madhun data Ghene
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        //Json madhun jo data alay to Data store karne
        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val datalist = response.body()?.data!!

                //Set Adapter
                myAdapter = MyAdapter(this@SongsListActivity,datalist)
                myrecyclerView.adapter = myAdapter

              myrecyclerView.layoutManager = LinearLayoutManager(this@SongsListActivity)

                myAdapter.setonItemClickListener(object :MyAdapter.onItemClickListener{

                    override fun onItemClick(position: Int) {
                       val intent =Intent(this@SongsListActivity , SongActivity::class.java)
                        startActivity(intent)

                    }
                })
            }
            override fun onFailure(call: Call<MyData?>, t: Throwable) {
            }
        })
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item ,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.signOut){
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this ,LoginActivity::class.java)
            startActivity(intent)
            finish()

            Toast.makeText(this, "User Sign Out", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

}
