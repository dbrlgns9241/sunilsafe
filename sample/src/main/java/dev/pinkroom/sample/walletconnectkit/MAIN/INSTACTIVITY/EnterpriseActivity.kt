package dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INST.ENTERINST.CeoFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INST.ENTERINST.OnwayFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INST.ENTERINST.VisionFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INST.ENTERINST.YearFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.DetailPagerAdapter
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.fragment_enterprise.*

class EnterpriseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        var viewPagers: ViewPager = enter_pager
        var tabLayouts: TabLayout = enter_tablayout

        var adapter = DetailPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(CeoFragment(), "CEO인사말")
        adapter.addFragment(YearFragment(), "연혁")
        adapter.addFragment(VisionFragment(), "비전")
        adapter.addFragment(OnwayFragment(), "오시는길")

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