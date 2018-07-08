package com.example.dl579.myapplication


import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.Manifest
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.File
import java.io.IOException
import okhttp3.RequestBody
import okhttp3.MultipartBody
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    val MUSIC_REQUEST = 1

    object fileInfo {
        var path: String? = null
        var url: Uri? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissonCheck()
        button4.setOnClickListener {
            runOnUiThread(Runnable {
                okHttpRequest(File(fileInfo.path), object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        Log.e("asdasd", response.toString())
                    }

                })
            })
        }
    }


    fun findMusic(v: View) {
        var intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MUSIC_REQUEST)
    }

    fun permissonCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MUSIC_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            fileInfo.url = data.data
            fileInfo.path = _getRealPathFromURI(this, data.data)
            Log.e("getPath: ", fileInfo.path)
        }
    }

    private fun _getRealPathFromURI(context: Any, contentUri: Uri): String? {
        var proj = arrayOf<String>(MediaStore.Audio.Media.DATA)
        var loader = CursorLoader(context as Context?, contentUri, proj, null, null, null)
        var cursor = loader.loadInBackground()
        var colum_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(colum_index)
    }

    fun okHttpRequest(file: File, callback: Callback) {
        val service = ServiceGenerator.createService(FileUploadService::class.java)


        val requestFile = RequestBody.create(
                MediaType.parse(contentResolver.getType(fileInfo.url)),
                file
        )
        Log.e("url: ",contentResolver.getType(fileInfo.url))
        Log.e("name ", file.toString())
        var groupName = JSONObject()
        groupName.put("groupName", "maroon5 Fans")
        // MultipartBody.Part is used to send also the actual file name
        val body1 = MultipartBody.Part.createFormData("audio", file.name, requestFile)
        val body2 = MultipartBody.Part.createFormData("text", groupName.toString())
        // add another part within the multipart request
        val descriptionString = "hello, this is description speaking"
        val description = RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString)
        var array = arrayListOf<MultipartBody.Part>()
        array.add(body1)
        array.add(body2)
        // finally, execute the request
        val call1 = service.upload(
                description,
                body1
        )
        val call2 = service.upload(
                description,
                body2
        )
        call1.enqueue(object:retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>?, t: Throwable?) {
                Log.e("Upload error:", t!!.message.toString())
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>?, response: retrofit2.Response<ResponseBody>?) {
                Log.e("Upload", "success")
            }
        })
        call2.enqueue(object:retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>?, t: Throwable?) {
                Log.e("Upload error:", t!!.message.toString())
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>?, response: retrofit2.Response<ResponseBody>?) {
                Log.e("Upload", "success")
            }
        })
    }
}
