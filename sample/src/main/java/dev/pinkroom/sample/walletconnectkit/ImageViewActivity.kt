package dev.pinkroom.sample.walletconnectkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
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