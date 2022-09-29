package dev.pinkroom.sample.walletconnectkit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //나의 NFT 갤러리 버튼
        button6.setOnClickListener {
            val intent = Intent(this, NFTActivity::class.java)
            startActivity(intent)
        }
    }
}