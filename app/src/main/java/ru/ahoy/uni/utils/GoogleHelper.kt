package ru.ahoy.uni.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import ru.ahoy.uni.R
import ru.ahoy.uni.listeners.AppValueEventListener
import ru.ahoy.uni.screens.activities.ScheduleActivity
import ru.ahoy.uni.screens.activities.SignInActivity

lateinit var GOOGLE_INTENT: Intent
private lateinit var gso: GoogleSignInOptions
private lateinit var googleSignInClient: GoogleSignInClient

private lateinit var mName: String
private lateinit var mEmail: String
private lateinit var mEmailConvert: String
private lateinit var idToken: String

private const val TAG = "GoogleHelper"
const val RC_SIGN_IN = 9001

fun AppCompatActivity.initGoogle() {
    Log.d(TAG, "initGoogle")
    gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.web_client_id))
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(this, gso)
    Log.d(TAG, googleSignInClient.toString())


    GOOGLE_INTENT = googleSignInClient.signInIntent
    Log.d(TAG, GOOGLE_INTENT.toString())
}

fun AppCompatActivity.handleSignInResult(result: GoogleSignInResult?) {
    if (result != null) {
        if (result.isSuccess) {
            val account = result.signInAccount
            idToken = account?.idToken.toString()
            mEmail = account?.email.toString()
            mName = mEmail.split("@")[0]
            mEmailConvert = mEmail.split(".")[0]

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuthWithGoogle(credential)
        } else showToast("Not successful")
    }
}

private fun AppCompatActivity.firebaseAuthWithGoogle(credential: AuthCredential) {
    AUTH.signInWithCredential(credential)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                checkUni()
            } else showToast(it.exception?.message.toString())
        }
}

private fun AppCompatActivity.checkUni() {
    REF_DATABASE_ROOT.child(NODE_USEREMAILS)
        .addListenerForSingleValueEvent(AppValueEventListener {
            if (!it.hasChild(mEmailConvert)) {
                initNodeUser()
            } else {
                showToast("Добро пожаловать!")
                replaceActivity(ScheduleActivity())
                finish()
            }
        })
}

private fun AppCompatActivity.initNodeUser() {
    val user = AUTH.currentUser
    val uid: String = user?.uid.toString()
    val dateMap: MutableMap<String, Any> = mutableMapOf<String, Any>()
    dateMap[CHILD_ID] = uid
    dateMap[CHILD_USERNAME] = mName
    dateMap[CHILD_SCHEDULE_ID] = ""
    dateMap[CHILD_PHOTO_URL] = ""

    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Добро пожаловать!")
                replaceActivity(ScheduleActivity())
                finish()
            } else showToast(task.exception?.message.toString())
        }

    REF_DATABASE_ROOT.child(NODE_USEREMAILS).child(mEmailConvert).setValue(AUTH.currentUser?.uid)
        .addOnCompleteListener {
            if(!it.isSuccessful) showToast("Database error")
        }
}

fun AppCompatActivity.signOut() {
    if(GoogleSignIn.getLastSignedInAccount(this) != null)
        googleSignInClient.signOut()

    AUTH.signOut()

    replaceActivity(SignInActivity())
    finish()
}
