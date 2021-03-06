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
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.File
import java.io.IOException
import okhttp3.RequestBody
import okhttp3.MultipartBody
import java.util.*


class MainActivity : AppCompatActivity() {
    private val MUSIC_REQUEST = 1
    private var recorder:MediaRecorder? = null
    object fileInfo {
        var path: String? = null
        var url: Uri? = null
    }
    private var mpath:String = ""
    private var media:MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissonCheck()
        recorder = MediaRecorder()

        sendMusic.setOnClickListener {
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
        startRecorder.setOnClickListener {
                initRecorder()
                recorder!!.prepare()
                recorder!!.start()

                play.isEnabled = false
                musicStopButton.isEnabled = false
        }
        RecorderStop.setOnClickListener {
            recorder!!.stop()
            play.isEnabled = true
            startRecorder.isEnabled = true
            RecorderStop.isEnabled = false
            musicStopButton.isEnabled = false
        }
        play.setOnClickListener {
            RecorderStop.isEnabled = false
            musicStopButton.isEnabled = true
            startRecorder.isEnabled = false
            media = MediaPlayer()
            media!!.setDataSource(mpath)
            media!!.prepare()
            media!!.start()
        }
        musicStopButton.setOnClickListener {
            play.isEnabled = true
            startRecorder.isEnabled = true
            RecorderStop.isEnabled = false
            musicStopButton.isEnabled = false
            if (media != null){
                media!!.stop()
                media!!.release()
                initRecorder()
            }

        }
    }

    fun initRecorder(){
        recorder!!.reset()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mpath = Environment.getExternalStorageDirectory().absolutePath+"/"+UUID.randomUUID().toString()+"_audio_record.3gp"
        recorder!!.setOutputFile(mpath)
        Log.e("path ",mpath)
    }

    fun findMusic(v: View) {
        var intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MUSIC_REQUEST)
    }

    fun permissonCheck() {
        var ReadStoragetPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        var ReadAudioPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        var WriteStorage= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (ReadAudioPermmission != PackageManager.PERMISSION_GRANTED && ReadStoragetPermmission != PackageManager.PERMISSION_GRANTED &&
                WriteStorage != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1000)
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

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("audio", file.name, requestFile)
        // add another part within the multipart request
        val descriptionString = "hello, this is description speaking"
        val description = RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString)

        // finally, execute the request
        val call = service.upload(
                description,
                body
        )

        call.enqueue(object:retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>?, t: Throwable?) {
                Log.e("Upload error:", t!!.message.toString())
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>?, response: retrofit2.Response<ResponseBody>?) {
                Log.e("Upload", "success")
            }
        })
    }
}
