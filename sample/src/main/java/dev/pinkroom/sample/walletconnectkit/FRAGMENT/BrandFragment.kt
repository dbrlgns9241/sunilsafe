package dev.pinkroom.sample.walletconnectkit.FRAGMENT

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.BRANDINST.EaglesafeFragment
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.BRANDINST.LucellFragment
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.fragment_brand.*


class BrandFragment : Fragment() {

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
        myFragment = inflater.inflate(R.layout.fragment_brand, container, false)
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
        adapter.addFragment(LucellFragment(), "루셀")
        adapter.addFragment(EaglesafeFragment(), "이글세이프")


        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }
}
