package ru.ahoy.uni.screens.fragments.auth

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_switch_login.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.SwitchLoginAdapter
import ru.ahoy.uni.screens.fragments.BaseFragment

class SwitchLoginFragment : BaseFragment(R.layout.fragment_switch_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager_sl.adapter = SwitchLoginAdapter(this)
        pager_sl.offscreenPageLimit = 2
    }

    fun nextPage() {
        pager_sl.currentItem++
    }

    fun prevPage() {
        pager_sl.currentItem--
    }
}