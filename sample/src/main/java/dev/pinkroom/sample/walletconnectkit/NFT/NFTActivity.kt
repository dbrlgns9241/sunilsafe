package dev.pinkroom.sample.walletconnectkit.NFT

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dev.pinkroom.sample.walletconnectkit.R
import dev.pinkroom.walletconnectkit.WalletConnectKit
import dev.pinkroom.walletconnectkit.WalletConnectKitConfig

import kotlinx.android.synthetic.main.activity_nft.*

class NFTActivity : AppCompatActivity() {

    // addressTxt 가 현재 연결된 이더리움 주소
    var addressTxt: String = ""

    // wallet connect check 용 프라이빗 변수
    private val config by lazy {
        WalletConnectKitConfig(
            context = this,
            bridgeUrl = "wss://bridge.aktionariat.com:8887",
            appUrl = "walletconnectkit.com",
            appName = "WalletConnect Kit",
            appDescription = "This is the Swiss Army toolkit for WalletConnect!"
        )
    }
    private val walletConnectKit by lazy { WalletConnectKit.Builder(config).build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nft)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        wc_button.start(walletConnectKit, ::onConnected, ::onDisconnected)
        nft_check_button.setOnClickListener {
            val addTxt = addressTxt
            val intent = Intent(this, CrawlingActivity::class.java)
            intent.putExtra("addTxt", addTxt)
            startActivity(intent)
        }

        logout_button.setOnClickListener{
            onDisconnectClicked()
        }
    }

    private fun onConnected(address: String) {
        addressTxt = getString(R.string.connected_with, address)
        wc_button.visibility = View.GONE
        nft_check_button.visibility = View.VISIBLE
        logout_button.visibility = View.VISIBLE
        invalidateOptionsMenu()
    }

    private fun onDisconnected() {
        wc_button.visibility = View.VISIBLE
        nft_check_button.visibility = View.GONE
        logout_button.visibility = View.GONE
        invalidateOptionsMenu()
    }

    private fun onDisconnectClicked() {
        walletConnectKit.removeSession()
    }

}