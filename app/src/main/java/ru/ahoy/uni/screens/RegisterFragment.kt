package ru.ahoy.uni.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_register.*
import ru.ahoy.uni.R
import ru.ahoy.uni.listeners.OnFocusListener
import ru.ahoy.uni.utils.*


class RegisterFragment(private var fragment: SwitchLoginFragment) :
    BaseFragment(R.layout.fragment_register) {

    private lateinit var mEmail: String
    private lateinit var mUsername: String
    private lateinit var mPassword: String

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reg_ed_login.onFocusChangeListener = OnFocusListener(reg_profile_icon, "login")
        reg_ed_password.onFocusChangeListener = OnFocusListener(reg_password_icon, "password")
        reg_ed_email.onFocusChangeListener = OnFocusListener(reg_email_icon, "email")
        reg_tv_login.setOnClickListener {
            fragment.prevPage()
        }

        initListeners()
    }

    private fun initListeners() {
        register_btn_register.setOnClickListener {
            regUser()
        }
    }

    private fun regUser() {
        mEmail = reg_ed_email.text.toString()
        mUsername = reg_ed_login.text.toString()
        mPassword = reg_ed_password.text.toString()


        when {
            mEmail.isEmpty() -> showToast("Email не может быть пустым")
            mUsername.isEmpty() -> showToast("Имя пользователя не может быть пустым")
            mPassword.isEmpty() -> showToast("Пароль не может быть пустым")
            else -> AUTH.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        val uid: String = AUTH.currentUser?.uid.toString()
                        val dateMap: MutableMap<String, Any> = mutableMapOf<String, Any>()
                        dateMap[CHILD_ID] = uid
                        dateMap[CHILD_USERNAME] = mUsername
                        dateMap[CHILD_SCHEDULE_ID] = ""
                        dateMap[CHILD_PHOTO_URL] = ""

                        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    logIn()
                                    showToast("Добро пожаловать!")
                                } else showToast(task.exception?.message.toString())
                            }
                    } else showToast(it.exception?.message.toString())
                }
        }
    }
    private fun logIn() {
        AUTH.signInWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val intent = Intent(this.context, ScheduleActivity::class.java)
                    startActivity(intent)
                    this.activity?.finish()
                } else showToast(it.exception?.message.toString())
            }
     }
}