package ru.ahoy.uni.adapters

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.screens.fragments.DayFragment
import ru.ahoy.uni.screens.fragments.DayOffFragment

class ScheduleFragmentAdapter(
    fragment: Fragment,
    private var subjectsLiveData: MutableLiveData<MutableSet<Subject>>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        val subjects: MutableSet<Subject> = mutableSetOf()
        for (s in subjectsLiveData.value!!) {
            if (s.day == position) {
                subjects.add(s)
            }
        }
        return if(subjects.isNotEmpty()) DayFragment(position, subjects)
        else DayOffFragment()
    }
}