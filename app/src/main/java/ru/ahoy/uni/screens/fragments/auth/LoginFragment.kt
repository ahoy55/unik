package ru.ahoy.uni.screens.fragments.auth

import kotlinx.android.synthetic.main.fragment_login.*
import ru.ahoy.uni.R
import ru.ahoy.uni.listeners.OnFocusListener
import ru.ahoy.uni.screens.fragments.BaseFragment
import ru.ahoy.uni.utils.GoogleAuth
import ru.ahoy.uni.utils.firebaseAuth
import ru.ahoy.uni.utils.replaceActivity

class LoginFragment(private var fragment: SwitchLoginFragment) :
    BaseFragment(R.layout.fragment_login) {

    override fun onStart() {
        super.onStart()

        initListeners()
    }

    private fun initListeners() {
        login_ed_login.onFocusChangeListener = OnFocusListener(login_email_icon, "email")
        login_ed_password.onFocusChangeListener = OnFocusListener(login_password_icon, "password")

        login_tv_registry.setOnClickListener {
            fragment.nextPage()
        }

        login_btn_continue.setOnClickListener {
            firebaseAuth(
                login_ed_login?.text.toString(),
                login_ed_password?.text.toString()
            )
        }

        login_btn_login_google.setOnClickListener {
            replaceActivity(GoogleAuth())
        }
    }
}