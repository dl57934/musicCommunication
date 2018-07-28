package hack.the.wap.musicinstrumentlessoner

import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaRecorder
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText


import java.util.UUID
import android.os.SystemClock
import android.widget.Toast


class recordActivity : AppCompatActivity() {
    internal var recorder: MediaRecorder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        val editText =findViewById<EditText>(R.id.editText)
        val startRecord = findViewById<Button>(R.id.startRecord)
        val saveRecord = findViewById<Button>(R.id.saveRecord)
        val stopRecord = findViewById<Button>(R.id.stopRecord)
        recorder = MediaRecorder()
        startRecord.setOnClickListener{
            var fileName = editText.text.toString()
            initRecorder(fileName)
            recorder!!.prepare()
            recorder!!.start()
            startRecord!!.isEnabled = false
            saveRecord!!.isEnabled = false
            stopRecord!!.isEnabled = true
            chronometer.start()
        }
        stopRecord!!.setOnClickListener {
            recorder!!.stop()
            startRecord!!.isEnabled = true
            stopRecord!!.isEnabled = false
            saveRecord!!.isEnabled = false
            chronometer.stop()
        }
        saveRecord!!.setOnClickListener{
            chronometer.base = SystemClock.elapsedRealtime()
            var fileName = editText.text.toString()
//            saveRecorder(fileName)
        }
    }

    private fun initRecorder(fileName:String) {
        recorder!!.reset()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        var file = Environment.getExternalStorageDirectory()
        var path = file.absolutePath+"/"
        val mpath = "$path$fileName.mp3"
        Log.e("path", mpath)
        recorder!!.setOutputFile(mpath)
    }

    private fun saveRecorder(fileName:String){
        var file = Environment.getExternalStorageDirectory()
        var path = file.absolutePath+"/"
        val mpath = "$path$fileName.mp3"
        if(fileName != null){
            recorder!!.setOutputFile(mpath)
            Log.e("path ", mpath)
            recorder!!.stop()
            recorder!!.release()
            recorder!!.reset()

        }else{
            Toast.makeText(this, "file 이름을 지정해주세요.", Toast.LENGTH_LONG)
        }

    }
}
