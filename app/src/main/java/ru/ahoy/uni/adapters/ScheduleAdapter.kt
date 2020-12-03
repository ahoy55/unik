package ru.ahoy.uni.adapters

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.ahoy.uni.models.Subject
import ru.ahoy.uni.screens.DayFragment

class ScheduleAdapter(
    fragment: Fragment,
    private val subjectsLiveData: MutableLiveData<MutableSet<Subject>>
) : FragmentStateAdapter(fragment) {
    private val ITEM_COUNT_PAGE: Int = 7

    override fun getItemCount(): Int {
        return ITEM_COUNT_PAGE
    }

    override fun createFragment(position: Int): Fragment {
        return DayFragment(position, subjectsLiveData)
    }
}