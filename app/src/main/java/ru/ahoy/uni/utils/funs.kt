package ru.ahoy.uni.utils

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.afollestad.materialdialogs.MaterialDialog
import ru.ahoy.uni.R
import ru.ahoy.uni.screens.fragments.EmptyScheduleFragment

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun FragmentActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.confirmExit() {
    val dialog = MaterialDialog(this)
        .title(text = "Выход")
        .message(text = "Нажмите ОК, чтобы выйти из приложения")
    dialog.show {
        positiveButton(text = "OK") {
            finishAffinity()
        }
        negativeButton(text = "Отмена") {
            dialog.cancel()
        }
    }
}

fun Fragment.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this.context, activity::class.java)
    startActivity(intent)
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
}

fun AppCompatActivity.replaceFragment(
    container: Int,
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction()
        .replace(
            container,
            fragment
        )
        .commit()
}

