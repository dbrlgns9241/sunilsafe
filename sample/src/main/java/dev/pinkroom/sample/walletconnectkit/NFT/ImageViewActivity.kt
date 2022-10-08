package dev.pinkroom.sample.walletconnectkit.NFT


import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_imageview.*
import java.io.File
import java.nio.file.Files.createFile


class ImageViewActivity : AppCompatActivity(){
    lateinit var data : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val imageView = findViewById<ImageView>(R.id.img_profile)
        Log.d("Taaaa", "여기까진 됨")

        //현재 선택한 이미지 url
        data = intent.getStringExtra("data").toString()
        
        //img url 로 imageview에 보여주기
        Glide.with(this).load(data).into(imageView)

        // 이미지 클릭시 버튼이 사라졌다 생겼다
        imageView.setOnClickListener{
            if(cloud_btn.visibility == View.GONE) {
                cloud_btn.visibility = View.VISIBLE
            }else{
                cloud_btn.visibility = View.GONE
            }
        }

        //클라우드로 전송 버튼
        cloud_btn.setOnClickListener{
            val database = Firebase.database
            val myRef = database.getReference("message")
            //realtime db에 이미지url 전송
            myRef.setValue(data)
            Toast.makeText(this, "클라우드로 전송완료", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}