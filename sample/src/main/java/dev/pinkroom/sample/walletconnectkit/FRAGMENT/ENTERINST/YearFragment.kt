package dev.pinkroom.sample.walletconnectkit.FRAGMENT.ENTERINST

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.pinkroom.sample.walletconnectkit.R


class YearFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_year, container, false)
    }

    fun newInstant() : YearFragment
    {
        val args = Bundle()
        val frag = YearFragment()
        frag.arguments = args
        return frag
    }

}