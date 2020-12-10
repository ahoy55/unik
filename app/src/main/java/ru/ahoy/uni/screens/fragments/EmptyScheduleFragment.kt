package ru.ahoy.uni.screens.fragments

import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.fragment_empty_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.screens.activities.ScheduleActivity

class EmptyScheduleFragment(private var subjectsLiveData: MutableLiveData<MutableSet<Subject>>) : BaseFragment(R.layout.fragment_empty_schedule) {

    override fun onStart() {
        super.onStart()

        schedule_btn_create.setOnClickListener {
            createSchedule()
        }

    }

    private fun createSchedule() {
        (this.activity as ScheduleActivity).supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.schedule_container,
                CreateScheduleFragment(
                    subjectsLiveData
                )
            )
            .commit()
    }
}