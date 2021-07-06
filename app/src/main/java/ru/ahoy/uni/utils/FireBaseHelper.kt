package ru.ahoy.uni.utils

import androidx.fragment.app.Fragment
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ahoy.uni.listeners.AppValueEventListener
import ru.ahoy.uni.models.User
import ru.ahoy.uni.screens.activities.ScheduleActivity

private val TAG: String = "FireBaseHelper"
lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User
lateinit var UID: String

const val NODE_USERS = "users"
const val NODE_USEREMAILS = "useremails"
const val NODE_SCHEDULES = "schedules"

const val CHILD_ID = "id"
const val CHILD_USERNAME = "username"
const val CHILD_SCHEDULE_ID = "schedule_id"
const val CHILD_PHOTO_URL = "photo_url"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
    Log.d(TAG, "UID = $UID")
}

fun initUser(liveData: MutableLiveData<Status>) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(User::class.java) ?: User()
            Log.d(TAG, "UID ${USER.id} \n USERNAME ${USER.username}")
            liveData.value = Status.SUCCESS
        })
}

fun Fragment.firebaseAuth(email: String, password: String) {

    if (email.isEmpty() || password.isEmpty()) {
        showToast("Email и пароль не могут быть пустыми")
    } else {
        AUTH.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Successful")
                    val intent = Intent(this.context, ScheduleActivity::class.java)
                    startActivity(intent)
                    this.activity?.finish()
                } else showToast(it.exception?.message.toString())
            }
    }
}