package dev.pinkroom.sample.walletconnectkit.FRAGMENT

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.fragment_enterprise.*

class EnterpriseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enterprise, container, false)
    }
}