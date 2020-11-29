package ru.ahoy.unik.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.ahoy.unik.screens.LoginFragment
import ru.ahoy.unik.screens.RegisterFragment
import ru.ahoy.unik.screens.SwitchLoginFragment

class SwitchLoginAdapter(private val fragment: SwitchLoginFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if(position == 0) {
            return LoginFragment(fragment)
        }
        return RegisterFragment(fragment)
    }
}