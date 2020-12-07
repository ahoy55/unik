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
import ru.ahoy.uni.listeners.AppValueEventListener
import ru.ahoy.uni.screens.activities.ScheduleActivity
import ru.ahoy.uni.screens.activities.SignInActivity

class GoogleSignIn() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)

        initFirebase()
        initGoogle()
    }

    override fun onStart() {
        super.onStart()

        startActivityForResult(GOOGLE_INTENT, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }
}