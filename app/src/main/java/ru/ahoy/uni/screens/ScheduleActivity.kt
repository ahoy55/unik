package ru.ahoy.uni.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.SignInActivity
import ru.ahoy.uni.databinding.ActivityScheduleBinding
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.utils.*

class ScheduleActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityScheduleBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mLiveData: MutableLiveData<Status>
    private val TAG = "ScheduleActivity"
    private lateinit var subjectsLiveData: MutableLiveData<MutableSet<Subject>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLiveData = MutableLiveData()
        subjectsLiveData = MutableLiveData()
        subjectsLiveData.value = mutableSetOf()

        initFirebase()
        initUser(mLiveData)

        mBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


    }

    override fun onStart() {
        super.onStart()
        mLiveData.observe(this, Observer {
            if(it == Status.SUCCESS) {
                if(USER.schedule_id.isEmpty()) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.schedule_container, EmptyScheduleFragment(subjectsLiveData))
                        .commit()
                }
                schedule_progress_bar.visibility = ProgressBar.INVISIBLE
            }
            else schedule_progress_bar.visibility = ProgressBar.VISIBLE
        })


    }



    private fun signOut() {
        if(GoogleSignIn.getLastSignedInAccount(this) != null) {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                AUTH.signOut()
            }
        } else AUTH.signOut()

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        confirmExit()
    }
}