package ru.ahoy.unik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import ru.ahoy.unik.databinding.ActivitySignInBinding
import ru.ahoy.unik.screens.SwitchLoginFragment
import kotlin.system.exitProcess

class SignInActivity() : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_sign_in, SwitchLoginFragment())
            .commit()
    }

    override fun onBackPressed() {
        val dialog = MaterialDialog(this)
            .title(text = "Выход")
            .message(text = "Нажмите ОК, чтобы выйти")
        dialog.show {
            positiveButton (text = "OK") {
                finishAffinity()
            }
            negativeButton (text = "Отмена") {
                dialog.cancel()
            }
        }
    }
}