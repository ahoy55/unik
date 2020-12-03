package ru.ahoy.uni.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.ahoy.uni.models.User

private val TAG: String = "FireBaseHelper"
lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User
lateinit var UID: String

const val NODE_USERS = "users"
const val NODE_USEREMAILS = "useremails"

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