package dev.pinkroom.sample.walletconnectkit.FRAGMENT


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.ENTERINST.CeoFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.ENTERINST.OnwayFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.ENTERINST.VisionFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.ENTERINST.YearFragment
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.fragment_enterprise.*


class EnterpriseFragment : Fragment() {
    lateinit var myFragment: View
    lateinit var viewPagers: ViewPager
    lateinit var tabLayouts: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment = inflater.inflate(R.layout.fragment_enterprise, container, false)
        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()
        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }


    private fun setUpViewPager() {
        viewPagers = pager
        tabLayouts = tablayout

        var adapter = DetailPagerAdapter(requireFragmentManager())
        adapter.addFragment(CeoFragment(), "CEO인사말")
        adapter.addFragment(YearFragment(), "연혁")
        adapter.addFragment(VisionFragment(), "비전")
        adapter.addFragment(OnwayFragment(), "오시는길")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }
}