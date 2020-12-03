package ru.ahoy.uni.screens

import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ahoy.uni.R
import ru.ahoy.uni.listeners.OnFocusListener
import ru.ahoy.uni.utils.AUTH
import ru.ahoy.uni.utils.GoogleSignInHelper
import ru.ahoy.uni.utils.showToast

class LoginFragment(private var fragment: SwitchLoginFragment) :
    BaseFragment(R.layout.fragment_login) {

    private lateinit var mPassword: String
    private lateinit var mName: String
    private lateinit var mEmail: String

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
            firebaseAuth()
        }

        login_btn_login_google.setOnClickListener {
            val intent = Intent(this.context, GoogleSignInHelper::class.java)
            startActivity(intent)
//            this.activity?.finish()
        }
    }

    private fun firebaseAuth() {
        mEmail = login_ed_login.text.toString()
        mPassword = login_ed_password.text.toString()

        if (mEmail.isEmpty() || mPassword.isEmpty()) {
            showToast("Email и пароль не могут быть пустыми")
        } else {
            AUTH.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Successful")
                        val intent = Intent(this.context, ScheduleActivity::class.java)
                        startActivity(intent)
                        this.activity?.finish()
                    } else showToast(it.exception?.message.toString())
                }
        }
    }

}