package ru.ahoy.uni.screens

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_day.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.AddDayRecyclerViewAdapter
import ru.ahoy.uni.models.Subject


class DayFragment(
    private val pos: Int,
    private var subjectsLiveData: MutableLiveData<MutableSet<Subject>>
) : BaseFragment(R.layout.fragment_day) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val day: String = when (pos) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            6 -> "Sunday"
            else -> "none"
        }

        day_tv.text = day


        day_rw.adapter = AddDayRecyclerViewAdapter(subjectsLiveData, pos, this.viewLifecycleOwner)
        day_rw.layoutManager = LinearLayoutManager(this.context)

    }

    override fun onPause() {
        super.onPause()
        val subjectsList = subjectsLiveData.value!!
        for (i in 0..7) {
            val editText =
                day_rw.findViewHolderForAdapterPosition(i)?.itemView?.findViewById<EditText>(R.id.rv_ed)
            if (editText != null && editText.text.isNotEmpty()) {
                subjectsList.add(Subject(editText.text.toString(), pos, i))
                break
            }
        }
        subjectsLiveData.value = subjectsList
    }
}
