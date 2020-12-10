package ru.ahoy.uni.screens.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.databinding.ActivityScheduleBinding
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.screens.fragments.EmptyScheduleFragment
import ru.ahoy.uni.screens.fragments.ScheduleFragment
import ru.ahoy.uni.utils.*

class ScheduleActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityScheduleBinding
    private lateinit var statusLiveData: MutableLiveData<Status>
    private val TAG = "ScheduleActivity"
    private lateinit var subjectsLiveData: MutableLiveData<MutableSet<Subject>>
    private lateinit var mToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

    }

    override fun onStart() {
        super.onStart()

        initFields()
        initFirebase()
        initUser(statusLiveData)
        initFunc()
    }

    private fun initFunc() {
        statusLiveData.observe(this, Observer {
            if (it == Status.SUCCESS) {
                if (USER.schedule_id.isEmpty()) {
                    replaceFragment(R.id.schedule_container, EmptyScheduleFragment(subjectsLiveData))
                } else {
                    replaceFragment(R.id.schedule_container, ScheduleFragment())
                }
                schedule_progress_bar.visibility = ProgressBar.INVISIBLE
            } else schedule_progress_bar.visibility = ProgressBar.VISIBLE
        })


        setSupportActionBar(mToolBar)
    }

    private fun initFields() {
        statusLiveData = MutableLiveData()
        subjectsLiveData = MutableLiveData()
        subjectsLiveData.value = mutableSetOf()

        mToolBar = mBinding.scheduleToolbar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_schedule, menu)
        initGoogle()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_exit) {
            signOut()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        confirmExit()
    }
}