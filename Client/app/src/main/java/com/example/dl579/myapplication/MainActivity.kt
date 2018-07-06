package com.example.dl579.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var url = "https://localhost:3000/test"
    var queue: RequestQueue? = null
    var stringRequest: StringRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
        stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { }, Response.ErrorListener { })
    }
    fun sendMusic(v: View) {
        Log.e("button", "click");
    }
}
