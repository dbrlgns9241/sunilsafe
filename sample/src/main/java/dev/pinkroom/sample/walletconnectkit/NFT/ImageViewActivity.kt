package dev.pinkroom.sample.walletconnectkit.NFT

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import dev.pinkroom.sample.walletconnectkit.MAIN.MainActivity
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_imageview.*

class ImageViewActivity : AppCompatActivity(){
    lateinit var data : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView = findViewById<ImageView>(R.id.img_profile)
        Log.d("Taaaa", "여기까진 됨")

        data = intent.getStringExtra("data").toString()

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
            //클라우드로 전송하는 기능 구현하면 됌
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