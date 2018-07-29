package hack.the.wap.musicinstrumentlessoner

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject



object VolleyService {
    fun volleyFunctions(context: Context, data:JSONObject, routerPath:String) {
        var url = "http://172.30.1.9:3000/$routerPath"
        var queue = Volley.newRequestQueue(context)
        var req = JsonObjectRequest(Request.Method.POST, url, data,
                Response.Listener { response ->
                    Log.e("success", response["soccerTeam"].toString())
                },
                Response.ErrorListener {
                })
        queue.add(req)
    }
}