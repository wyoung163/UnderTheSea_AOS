package com.example.underthesea_aos.record

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.storage.FirebaseStorage
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.activity_record.view.*
import java.text.SimpleDateFormat
import java.util.*

class RecordImageFragment : AppCompatActivity() {
    private var imageFragment: View? = null
    var pickImageFromAlbum = 0
    var fbStorage: FirebaseStorage? = null
    var uriPhoto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageFragment!!.btn_UploadPicture.setOnClickListener {
            onCreateView()
        }
    }

    //파이어 베이스 초기화하고 upload 클릭 시 동작할 리스너 구성
    fun onCreateView(){
        Log.d("rrhhhhhhhhhhhhtfufj", "rrr")
        //imageFragment = inflater.inflate(R.layout.activity_record, container, false)

        //firebase storage 초기화
        fbStorage = FirebaseStorage.getInstance()

        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, pickImageFromAlbum)

        //return imageFragment
    }

    //앨범에서 사진 선택 시 동작
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImageFromAlbum) {
            if (resultCode == Activity.RESULT_OK) {
                //path for selected image
                uriPhoto = data?.data
                user_image.setImageURI(uriPhoto)

                if (ContextCompat.checkSelfPermission(
                        imageFragment!!.context,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    funImageUpload(imageFragment!!)
                }
            } else {

            }
        }
    }

    //사진 선택 후 해당 사진 imageView에 도출 하고 파이어베이스에 업로드
    private fun funImageUpload(view: View) {
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imgFileName = "IMAGE_" + timeStamp + "_.png"
        var storageRef = fbStorage?.reference?.child("images")?.child(imgFileName)

        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
            Toast.makeText(
                view.context, "Image Uploaded",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}