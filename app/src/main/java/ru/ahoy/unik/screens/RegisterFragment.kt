package ru.ahoy.unik.screens

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_register.*
import ru.ahoy.unik.R
import ru.ahoy.unik.listeners.OnFocusListener


class RegisterFragment(private var fragment: SwitchLoginFragment) : BaseFragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reg_ed_login.onFocusChangeListener = OnFocusListener(reg_profile_icon, "login")
        reg_ed_password.onFocusChangeListener = OnFocusListener(reg_password_icon, "password")
        reg_ed_phone.onFocusChangeListener = OnFocusListener(reg_phone_icon, "phone")
        reg_tv_login.setOnClickListener {
            fragment.prevPage()
        }
    }
}