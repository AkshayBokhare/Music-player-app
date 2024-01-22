package com.nts.musicplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.nts.musicplayer.data.Data
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.logging.Handler


open class MyAdapter(val context: Activity, val datalist : List<Data>):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

             var totalTime:Int=0

            lateinit var mLickListener :onItemClickListener

            interface onItemClickListener{
                fun onItemClick(position: Int)
            }

        fun setonItemClickListener(listener:onItemClickListener){
            mLickListener = listener
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // here We create the view in case the layout manager fails to create view for data
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_song,parent,false)
        return MyViewHolder(itemView,mLickListener)
    }

    override fun getItemCount(): Int {
       return  datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentData = datalist[position]

        //set tittle in recycler View
        holder.musicTitle.text = currentData.title
        //set Image in recycler View
        Picasso.get().load(currentData.album.cover_big).into(holder.musicImage);

        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())

        holder.imageButton_play.setOnClickListener {
            mediaPlayer.start()
        }
        holder.imageButton_pause.setOnClickListener {
            mediaPlayer.pause()
        }
        holder.imageButton_stop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        //when user change the timestamp of music reflect that change

        holder.seekbarMusic.max
        //Change the seek bar position base on the music
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    holder.seekbarMusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exeption: java.lang.Exception) {
                    holder.seekbarMusic.progress = 0
                }
            }
        }, 0)

    }

    class MyViewHolder (itemView:View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val musicImage :CircleImageView
        val musicTitle :TextView
        val imageButton_play : CircleImageView
        val imageButton_pause : CircleImageView
        val imageButton_stop : CircleImageView
        val seekbarMusic : SeekBar

        init {
            musicImage = itemView.findViewById(R.id.songProfile_image)
            musicTitle = itemView.findViewById(R.id.song_Title)
            imageButton_play =itemView.findViewById(R.id.imageButton_play)
            imageButton_pause =itemView.findViewById(R.id.imageButton_pause)
            imageButton_stop =itemView.findViewById(R.id.imageButton_stop)
            seekbarMusic =itemView.findViewById(R.id.seekBar)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
