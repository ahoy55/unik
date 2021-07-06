package ru.ahoy.uni.adapters

import android.content.Context
import android.text.Layout
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*
import ru.ahoy.uni.R
import ru.ahoy.uni.models.Subject

class DayRecyclerViewAdapter(
    private var day: Int,
    private var subjects: MutableSet<Subject>,
    private var recyclerView: RecyclerView,
    private var context: Context
) :
    RecyclerView.Adapter<DayRecyclerViewAdapter.ViewHolder>() {

    private var mExpandedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isFree = true
        val isExpanded: Boolean

        holder.charTitle?.text = "F"
        holder.title?.text = "free"

        for (subject in subjects) {
            if (subject.number == position) {
                holder.title?.text = subject.name
                holder.charTitle?.text = subject.name.toCharArray()[0].toString().toUpperCase()
                isFree = false
                break
            }
        }

        if (!isFree) {
            isExpanded = position == mExpandedPosition
            holder.details?.visibility =
                if (isExpanded) View.VISIBLE
                else View.GONE
            holder.header?.isActivated = isExpanded
            holder.buttonArrow?.setOnClickListener {
                mExpandedPosition = (if (isExpanded) -1 else position)
                notifyItemChanged(position)
            }
        } else {
            isExpanded = true
            holder.details?.visibility = View.GONE
            holder.charTitle?.visibility = View.INVISIBLE
            holder.title?.visibility = View.INVISIBLE
            holder.title?.visibility = View.INVISIBLE
            holder.freeTime?.visibility = View.VISIBLE
            holder.buttonArrow?.visibility = View.INVISIBLE
            holder.itemView.isActivated = false
            holder.itemView.isClickable = false

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var charTitle: TextView? = null
        var title: TextView? = null
        var time: TextView? = null
        var details: ViewGroup? = null
        var header: ViewGroup? = null
        var buttonArrow: ImageView? = null
        var freeTime: TextView? = null

        init {
            title = itemView.tv_title
            charTitle = itemView.tv_char_title
            time = itemView.tv_time
            details = itemView.details
            header = itemView.title_container
            buttonArrow = itemView.btn_arrow
            freeTime = itemView.tv_free_time
        }
    }

}