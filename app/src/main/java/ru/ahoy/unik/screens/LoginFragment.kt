package ru.ahoy.unik.screens

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ahoy.unik.R
import ru.ahoy.unik.listeners.OnFocusListener

class LoginFragment(private var fragment: SwitchLoginFragment) : BaseFragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login_ed_login.onFocusChangeListener = OnFocusListener(login_profile_icon, "login")
        login_ed_password.onFocusChangeListener = OnFocusListener(login_password_icon, "password")
        login_tv_registry.setOnClickListener {
            fragment.nextPage()
        }


    }

}