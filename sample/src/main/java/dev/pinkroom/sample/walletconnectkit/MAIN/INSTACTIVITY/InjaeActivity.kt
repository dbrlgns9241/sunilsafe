package dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.DetailPagerAdapter
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INSTFRAGMENT.INJAE.IbsajiwonFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.INSTFRAGMENT.INJAE.InjaesangFragment
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_injae.*

class InjaeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dev.pinkroom.sample.walletconnectkit.R.layout.activity_injae)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        var viewPagers: ViewPager = injae_pager
        var tabLayouts: TabLayout = injae_tablayout
        var adapter = DetailPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(InjaesangFragment(), "인재상")
        adapter.addFragment(IbsajiwonFragment(), "입사지원")

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