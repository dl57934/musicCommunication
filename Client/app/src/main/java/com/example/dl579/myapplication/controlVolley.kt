package com.example.dl579.myapplication

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


object VolleyService {
    var url = "http://192.168.43.47:3000/test"
    fun volleyFuctions(context:Context) {
        var queue = Volley.newRequestQueue(context)
        var jsonRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {response ->
            Log.e("success", response["soccerTeam"].toString())
        }, Response.ErrorListener {

        })

        queue.add(jsonRequest)
    }
}