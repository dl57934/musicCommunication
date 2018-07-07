package com.example.dl579.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.ContextMenu
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


object VolleyService {
    var url = "http://192.168.43.47:3000/test"
    var maxBuffer = 1 * 1024 * 1024
    fun volleyFuctions(context:Context, path:String?) {
        var queue = Volley.newRequestQueue(context)
        var sendData = JSONObject()
        sendData.put("groupName", "maroon5 Fans")
        sendData.put("musicFile", file2Byte(path))
        var req = JsonObjectRequest(Request.Method.POST, url, sendData, Response.Listener {response ->
            Log.e("success", response["soccerTeam"].toString())
        }, Response.ErrorListener {
        })
        queue.add(req)
    }
    fun file2Byte(path:String?): ByteArray {
        var file = File(path)
        var size = file.length().toInt()
        var bytes = ByteArray(size)
        var buf = BufferedInputStream(FileInputStream(file))
        buf.read(bytes, 0, bytes.size)
        Log.e("ㅁㄴㅇㅁㄴㅇ: ",java.math.BigInteger(bytes).toString(16))
        return bytes
    }

}


