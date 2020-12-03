package ru.ahoy.uni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import ru.ahoy.uni.databinding.ActivitySignInBinding
import ru.ahoy.uni.screens.SwitchLoginFragment
import ru.ahoy.uni.utils.confirmExit
import ru.ahoy.uni.utils.initFirebase

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

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_sign_in, SwitchLoginFragment())
            .commit()
    }

    override fun onBackPressed() {
        confirmExit()
    }
}