package ru.ahoy.uni.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.ahoy.uni.screens.fragments.auth.LoginFragment
import ru.ahoy.uni.screens.fragments.auth.RegisterFragment
import ru.ahoy.uni.screens.fragments.auth.SwitchLoginFragment

class SwitchLoginAdapter(private val fragment: SwitchLoginFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if(position == 0) {
            return LoginFragment(fragment)
        }
        return RegisterFragment(fragment)
    }
}