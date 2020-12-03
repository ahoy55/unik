package ru.ahoy.uni.utils

import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import ru.ahoy.uni.models.User

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(message: String) {
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
