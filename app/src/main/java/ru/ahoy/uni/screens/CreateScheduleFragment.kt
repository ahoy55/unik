package ru.ahoy.uni.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_create_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.ScheduleAdapter
import ru.ahoy.uni.models.Subject

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
        Log.d("CreateScheduleFragment", subjectsLiveData.value.toString())
        viewPager.currentItem++
    }
}