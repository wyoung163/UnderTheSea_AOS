package com.example.underthesea_aos.record

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.underthesea_aos.R
import com.example.underthesea_aos.databinding.ActivityMainBinding
import com.example.underthesea_aos.retrofit.RetrofitBuilder
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var result: TextView
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    private var imageFragment: View? = null
    var pickImageFromAlbum = 0
    var fbStorage: FirebaseStorage? = null
    var uriPhoto: Uri? = null

    var satisfaction = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        spinner = findViewById(R.id.spinner)
        var data = listOf("aaa", "bbb", "ccc");
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        spinner.adapter = adapter

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        btn_UploadPicture.setOnClickListener{
            onCreateView()
        }

        //글자수 제한
        with(binding){
            txt_content.addTextChangedListener(object : TextWatcher {
                var maxText = ""
                override fun beforeTextChanged(pos: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    maxText = pos.toString()
                }
                override fun onTextChanged(pos: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if(txt_content.lineCount > 3){
                        Toast.makeText(this@MainActivity,
                            "최대 3줄까지 입력 가능합니다.",
                            Toast.LENGTH_SHORT).show()

                        txt_content.setText(maxText)
                        txt_content.setSelection(txt_content.length())
                        count.setText("${txt_content.length()} / 30")
                    } else if(txt_content.length() > 30){
                        Toast.makeText(this@MainActivity, "최대 30자까지 입력 가능합니다.",
                            Toast.LENGTH_SHORT).show()

                        txt_content.setText(maxText)
                        txt_content.setSelection(txt_content.length())
                        count.setText("${txt_content.length()} / 30")
                    } else {
                        count.setText("${txt_content.length()} / 30")
                    }
                }
                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }

        //만족 버튼 클릭
        smile.setOnClickListener{
            satisfaction = 1

        }

        //불만족 버튼 클릭
        sad.setOnClickListener{
            satisfaction = 2
        }

        //백엔드와의 통신 성공 or 실패
        fun PostRecords(record: RecordInfo){
            record.img_url = uriPhoto.toString()
            record.satisfaction = satisfaction
            //record.date =
            //record.content =

            val call = RetrofitBuilder.retrofit().postRecordsResponse(record)
            //비동기 방식의 통신
            call.enqueue(object : Callback<String> {
                //통신 성공
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    //응답 성공
                    if(response.isSuccessful()){
                        Log.d("Response: ", response.body().toString())
                    }
                    //응답 실패
                    else{
                        Log.d("Response: ", "failure")
                    }
                }
                //통신 실패
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Connection Failure", t.localizedMessage)
                }
            })
        }
    }

    //파이어 베이스 초기화하고 upload 클릭 시 동작할 리스너 구성
    fun onCreateView(){
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