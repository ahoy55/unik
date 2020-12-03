package ru.ahoy.uni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ahoy.uni.databinding.ActivityMainBinding
import ru.ahoy.uni.screens.ScheduleActivity
import ru.ahoy.uni.utils.*


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val isLogin = false;

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
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
            this.finish()
        } else {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}