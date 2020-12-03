package ru.ahoy.uni.utils

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import ru.ahoy.uni.R
import ru.ahoy.uni.screens.ScheduleActivity

class GoogleSignInHelper() : AppCompatActivity() {

    private lateinit var mName: String
    private lateinit var mEmail: String
    private lateinit var mEmailConvert: String
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var idToken: String
    private val RC_SIGN_IN = 9001


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)

        initFirebase()
    }

    override fun onStart() {
        super.onStart()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult?) {
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

    private fun firebaseAuthWithGoogle(credential: AuthCredential) {
        AUTH.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    checkUni()
                } else showToast(it.exception?.message.toString())
            }
    }

    private fun checkUni() {
        REF_DATABASE_ROOT.child(NODE_USEREMAILS)
            .addListenerForSingleValueEvent(AppValueEventListener {
                if(!it.hasChild(mEmailConvert)) {
                    initNodeUser()
                }
                else {
                    showToast("Добро пожаловать!")
                    val intent = Intent(this, ScheduleActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
    }

    private fun initNodeUser() {
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
                    val intent = Intent(this, ScheduleActivity::class.java)
                    startActivity(intent)
                    finish()
                } else showToast(task.exception?.message.toString())
            }

        REF_DATABASE_ROOT.child(NODE_USEREMAILS).child(mEmailConvert).setValue(AUTH.currentUser?.uid)
            .addOnCompleteListener {
                if(!it.isSuccessful) showToast("Database error")
            }
    }
}