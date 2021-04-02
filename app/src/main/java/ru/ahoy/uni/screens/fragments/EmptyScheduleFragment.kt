package ru.ahoy.uni.screens.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

class FragmentStateHelper(val fragmentManager: FragmentManager) {

    private val fragmentSavedStates = mutableMapOf<String, Fragment.SavedState?>()

    fun restoreState(fragment: Fragment, key: String) {
        fragmentSavedStates[key]?.let { savedState ->
            if(!fragment.isAdded) {
                fragment.setInitialSavedState(savedState)
            }
        }
    }

    fun saveState(fragment: Fragment, key: String) {
        if (fragment.isAdded ?: false) {
            fragmentSavedStates[key] = fragmentManager.saveFragmentInstanceState(fragment)
        }
    }
}