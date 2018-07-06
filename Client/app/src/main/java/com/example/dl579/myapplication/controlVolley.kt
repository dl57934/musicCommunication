package com.example.dl579.myapplication

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


object VolleyService {
    var url = "http://192.168.43.47:3000/test"
    fun volleyFuctions(context:Context, path:String?) {
        var queue = Volley.newRequestQueue(context)
        var sendData = JSONObject()
        val musicData = musicFileChange(path)
        sendData.put("groupName", "maroon5 Fans")
        sendData.put("musicFile", musicData.toString())
        var jsonRequest = JsonObjectRequest(Request.Method.GET, url, sendData, Response.Listener {response ->
            Log.e("success", response["soccerTeam"].toString())
        }, Response.ErrorListener {

        })

        queue.add(jsonRequest)
    }

    fun musicFileChange(path:String?): ByteArray {
        val chunkSize = 1024*96
        val file = File(path)
        var byteData = file.readBytes()
        return byteData

    }
}


