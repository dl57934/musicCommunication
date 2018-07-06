package com.example.dl579.myapplication


import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent



class MainActivity : AppCompatActivity() {
    val MUSIC_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
   fun sendMusic(v: View){
        VolleyService.volleyFuctions(this)
    }
    fun findMusic(v:View){
        var intent =  Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MUSIC_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MUSIC_REQUEST && resultCode== Activity.RESULT_OK && data)
    }
}
