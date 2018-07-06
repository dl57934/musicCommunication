package com.example.dl579.myapplication


import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File


class MainActivity : AppCompatActivity() {
    val MUSIC_REQUEST = 1
    object file{
        var path:String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
   fun sendMusic(v: View){
        VolleyService.volleyFuctions(this, file.path)
    }
    fun findMusic(v:View){
        var intent =  Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MUSIC_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MUSIC_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            file.path = _getRealPathFromURI(this, data.data)
            Log.e("getPath: ", file.path)
        }
    }
    private fun _getRealPathFromURI(context:Any, contentUri: Uri): String? {
        var proj = arrayOf<String>(MediaStore.Audio.Media.DATA)
        var loader = CursorLoader(context as Context?, contentUri, proj, null, null, null)
        var cursor = loader.loadInBackground()
        var colum_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(colum_index)
    }
}
