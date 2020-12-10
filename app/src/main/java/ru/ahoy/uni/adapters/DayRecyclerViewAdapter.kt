package ru.ahoy.uni.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*
import ru.ahoy.uni.R
import ru.ahoy.uni.models.Subject

class DayRecyclerViewAdapter(private var day: Int, private var subjects: MutableSet<Subject>) :
    RecyclerView.Adapter<DayRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title?.text = position.toString()
        holder.name?.text = "free"

        for(subject in subjects) {
            if(subject.number == position) {
                holder.name?.text = subject.name
                break
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var name: TextView? = null

        init {
            title = itemView.rv_item_title
            name = itemView.rv_item_name
        }
    }

}