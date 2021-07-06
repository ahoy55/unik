package ru.ahoy.uni

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ahoy.uni.databinding.ActivityMainBinding
import ru.ahoy.uni.screens.activities.ScheduleActivity
import ru.ahoy.uni.screens.activities.SignInActivity
import ru.ahoy.uni.utils.AUTH
import ru.ahoy.uni.utils.initFirebase
import ru.ahoy.uni.utils.replaceActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        initFirebase()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            replaceActivity(ScheduleActivity())
        } else replaceActivity(SignInActivity())
        finish()
    }
}