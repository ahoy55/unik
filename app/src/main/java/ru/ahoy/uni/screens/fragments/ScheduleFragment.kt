package ru.ahoy.uni.screens.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.fragment_schedule.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.ScheduleFragmentAdapter
import ru.ahoy.uni.listeners.AppValueEventListener
import ru.ahoy.uni.models.Schedule
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.utils.*
import java.util.*

class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {

    private lateinit var statusLiveData: MutableLiveData<Status>
    private lateinit var subjectsLiveData: MutableLiveData<MutableSet<Subject>>
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusLiveData = MutableLiveData()
        subjectsLiveData = MutableLiveData()

        REF_DATABASE_ROOT.child(NODE_SCHEDULES).child(USER.schedule_id)
            .addListenerForSingleValueEvent(AppValueEventListener {
                statusLiveData.value = Status.SUCCESS
                subjectsLiveData.value = it.getValue(Schedule::class.java)?.subjects?.toMutableSet()
            })

        viewPager = schedule_fragment_vp
        viewPager.offscreenPageLimit = 7


    }

    override fun onStart() {
        super.onStart()
        statusLiveData.observe(this, Observer {
            if (statusLiveData.value == Status.SUCCESS) {
                viewPager.adapter = ScheduleFragmentAdapter(this, subjectsLiveData)

                TabLayoutMediator(
                    schedule_fragment_tl,
                    viewPager,
                    TabConfigurationStrategy { tab: TabLayout.Tab, i: Int ->
                        tab.text = when (i) {
                            0 -> "ПН"
                            1 -> "ВТ"
                            2 -> "СР"
                            3 -> "ЧТ"
                            4 -> "ПТ"
                            5 -> "СБ"
                            6 -> "ВС"
                            else -> "День недели"
                        }
                    }).attach()
                viewPager.currentItem = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2
            }
        })
    }


}