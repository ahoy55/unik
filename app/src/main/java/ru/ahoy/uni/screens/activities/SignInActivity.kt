package ru.ahoy.uni.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ahoy.uni.R
import ru.ahoy.uni.databinding.ActivitySignInBinding
import ru.ahoy.uni.screens.fragments.auth.SwitchLoginFragment
import ru.ahoy.uni.utils.confirmExit
import ru.ahoy.uni.utils.initFirebase
import ru.ahoy.uni.utils.replaceFragment

class SignInActivity() : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initFirebase()
    }

    override fun onStart() {
        super.onStart()

        replaceFragment(R.id.container_sign_in, SwitchLoginFragment())
    }

    override fun onBackPressed() {
        confirmExit()
    }
}