package com.example.dl579.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ContextMenu
import android.webkit.MimeTypeMap
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.*


object VolleyService {
    var url = "http://192.168.43.47:3000/test"
    var maxBuffer = 1 * 1024 * 1024
    fun volleyFuctions(context:Context, path:String?) {
        var queue = Volley.newRequestQueue(context)
        var sendData = JSONObject()
        var file = File(path)
        sendData.put("groupName", "maroon5 Fans")
        sendData.put("musicFile", file)
        var req = JsonObjectRequest(Request.Method.POST, url, sendData, Response.Listener {response ->
            Log.e("success", response["soccerTeam"].toString())
        }, Response.ErrorListener {
        })
        queue.add(req)
    }
    fun file2Byte(context:Context, path:String?): Bitmap? {

    }


}


