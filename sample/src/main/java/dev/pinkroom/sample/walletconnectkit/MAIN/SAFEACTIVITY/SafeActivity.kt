package dev.pinkroom.sample.walletconnectkit.MAIN.SAFEACTIVITY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.DetailPagerAdapter
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.SAFEFRAGMENT.InteriorFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.SAFEFRAGMENT.NaehwaFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.SAFEFRAGMENT.SamuFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.SAFEFRAGMENT.SpecialFragment

import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_safe.*

class SafeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        var viewPagers: ViewPager = safe_pager
        var tabLayouts: TabLayout = safe_tablayout
        var adapter = DetailPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(InteriorFragment(), "가정용 인테리어 금고")
        adapter.addFragment(SamuFragment(), "사무용 내화 금고")
        adapter.addFragment(NaehwaFragment(), "내화 방도 겸용 금고")
        adapter.addFragment(SpecialFragment(), "특수 금고")

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