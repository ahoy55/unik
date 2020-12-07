package ru.ahoy.uni.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_create_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.ScheduleAdapter
import ru.ahoy.uni.models.Schedule
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.utils.*
import java.util.*

class CreateScheduleFragment(private var subjectsLiveData: MutableLiveData<MutableSet<Subject>>) : BaseFragment(R.layout.fragment_create_schedule) {

    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = create_schedule_vp
        viewPager.adapter = ScheduleAdapter(this, subjectsLiveData)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
        viewPager.isUserInputEnabled = false

        create_schedule_prev.setOnClickListener {
            pageBack()
        }

        create_schedule_next.setOnClickListener {
            pageNext()
        }
    }

    private fun pageBack() {
        if (viewPager.currentItem != 0) {
            viewPager.currentItem--
        } else {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun pageNext() {
        if(viewPager.currentItem == 6) {
            val dialog = MaterialDialog(requireContext())
                .title(text = "Продолжить")
                .message(text = "Нажмите ОК, если хотите создать расписание")
            dialog.show {
                positiveButton(text = "OK") {
                    createSchedule()
                    val intent = Intent(requireContext(), ScheduleActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                negativeButton(text = "Отмена") {
                    dialog.cancel()
                }
            }
        } else viewPager.currentItem++
    }

    private fun createSchedule() {
        val schedule = Schedule(subjectsLiveData.value!!.toList(), USER.id, Calendar.getInstance().time.toString())

        val key = REF_DATABASE_ROOT.child(NODE_SCHEDULES).push().key.toString()
        REF_DATABASE_ROOT.child(NODE_SCHEDULES).child(key).setValue(schedule)

        REF_DATABASE_ROOT.child(NODE_USERS).child(USER.id).child(CHILD_SCHEDULE_ID).setValue(key)
    }
}