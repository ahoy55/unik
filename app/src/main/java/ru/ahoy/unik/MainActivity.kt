package ru.ahoy.unik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.ahoy.unik.databinding.ActivityMainBinding
import ru.ahoy.unik.screens.LoginFragment
import ru.ahoy.unik.screens.SwitchLoginFragment


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val isLogin = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if(isLogin) {

        }
        else {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}