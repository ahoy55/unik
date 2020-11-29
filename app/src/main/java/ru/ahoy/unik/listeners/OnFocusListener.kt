package ru.ahoy.unik.listeners

import android.view.View
import android.widget.ImageView
import ru.ahoy.unik.R

class OnFocusListener(private var icon: ImageView, private var typeIcon: String) : View.OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when(typeIcon) {
            "phone" -> when(hasFocus) {
                true -> icon.setImageResource(R.drawable.ic_register_phone_active)
                false -> icon.setImageResource(R.drawable.ic_register_phone)
            }
            "login" -> when (hasFocus) {
                true -> icon.setImageResource(R.drawable.ic_login_icon_active)
                false -> icon.setImageResource(R.drawable.ic_login_icon)
            }
            "password" -> when (hasFocus) {
                true -> icon.setImageResource(R.drawable.ic_password_icon_active)
                false -> icon.setImageResource(R.drawable.ic_password_icon)
            }
        }
    }
}