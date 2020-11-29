package ru.ahoy.unik.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_switch_login.*
import ru.ahoy.unik.R
import ru.ahoy.unik.adapters.SwitchLoginAdapter

class SwitchLoginFragment : BaseFragment(R.layout.fragment_switch_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager_sl.adapter = SwitchLoginAdapter(this)
    }

    fun nextPage() {
        pager_sl.currentItem++
    }

    fun prevPage() {
        pager_sl.currentItem--
    }
}