package hack.the.wap.musicinstrumentlessoner

import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import org.json.JSONObject

class addGroup : AppCompatActivity() {
    lateinit var ImageView:ImageView
    lateinit var addGroupButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__group)
        var jsonData = JSONObject()
        var groupName = findViewById<EditText>(R.id.groupName)
        var multiText = findViewById<EditText>(R.id.multiLineText)
        ImageView = findViewById<ImageView>(R.id.groupImage)
        addGroupButton = findViewById<Button>(R.id.addGroupButton)
        jsonData.put("groupName", groupName.text)
        jsonData.put("multiText", multiText.text)
        jsonData.put("Image", ImageView.resources)
        jsonData.put("member", "dl57934")
        addGroupButton.setOnClickListener {
        Log.e("logData", jsonData.toString())
        var volley = VolleyService
            volley.volleyFunctions(this,jsonData,"addGroup")
        }
    }
    fun addImage(v: View){
        var intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            var url = data.data
            var path = _getRealPathFromURI(this, url)
            var img = BitmapFactory.decodeFile(path)
            ImageView.setImageBitmap(img)
        }
    }
    private fun _getRealPathFromURI(context: Any, contentUri: Uri): String? {
        var proj = arrayOf<String>(MediaStore.Images.Media.DATA)
        var loader = CursorLoader(context as Context?, contentUri, proj, null, null, null)
        var cursor = loader.loadInBackground()
        var colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(colum_index)
    }
}
