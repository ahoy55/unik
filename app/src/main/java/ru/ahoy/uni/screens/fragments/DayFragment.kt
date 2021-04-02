package ru.ahoy.uni.screens.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_day.*
import kotlinx.android.synthetic.main.fragment_day.*
import ru.ahoy.uni.R
import ru.ahoy.uni.adapters.DayRecyclerViewAdapter
import ru.ahoy.uni.models.Subject

class DayFragment(private val day: Int, private val subjects: MutableSet<Subject>) :
    BaseFragment(R.layout.fragment_day) {
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = day_fragment_rv
        recyclerView.adapter = DayRecyclerViewAdapter(day, subjects, recyclerView, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(this.context)

    }
}