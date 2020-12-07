package ru.ahoy.uni.screens

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_switch_login.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.SwitchLoginAdapter

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