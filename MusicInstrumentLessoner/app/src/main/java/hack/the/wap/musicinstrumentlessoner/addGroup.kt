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
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView

class addGroup : AppCompatActivity() {
    lateinit var ImageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__group)
       ImageView = findViewById<ImageView>(R.id.groupImage)
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
