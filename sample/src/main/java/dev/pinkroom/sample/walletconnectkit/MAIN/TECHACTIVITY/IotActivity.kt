package dev.pinkroom.sample.walletconnectkit.MAIN.TECHACTIVITY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.DetailPagerAdapter
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.TECHFRAGMENT.IOT.IotlgFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.TECHFRAGMENT.IOT.IotsktFragment
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_iot.*

class IotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iot)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        var viewPagers: ViewPager = iot_pager
        var tabLayouts: TabLayout = iot_tablayout
        var adapter = DetailPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(IotsktFragment(), "SKT IOT 금고")
        adapter.addFragment(IotlgFragment(), "lGU+ IOT 금고")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)

        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }
}