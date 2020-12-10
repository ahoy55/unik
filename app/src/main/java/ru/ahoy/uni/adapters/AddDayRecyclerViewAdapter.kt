package ru.ahoy.uni.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_add.view.*
import ru.ahoy.uni.R
import ru.ahoy.uni.models.Subject

class AddDayRecyclerViewAdapter(
    private var subjectsLiveData: MutableLiveData<MutableSet<Subject>>,
    private var day: Int,
    private var context: LifecycleOwner
) : RecyclerView.Adapter<AddDayRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_add, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title?.text = (position + 1).toString()
//        for(s in subjectsLiveData.value!!) {
//            if(s.day == day) {
//                if(s.number == position) {
//                    holder.editText?.text
//                }
//            }
//        }
        subjectsLiveData.observe(context, Observer {
            for(s in subjectsLiveData.value!!) {
                if(s.day == day && s.number == position) {
                    holder.editText?.setText(s.name)
                }
            }
        })
        holder.editText?.onFocusChangeListener = View.OnFocusChangeListener { _, b: Boolean ->
            if(b) {
                holder.title?.setTextColor(Color.parseColor("#FF6200EE"))
            } else holder.title?.setTextColor(Color.parseColor("#FF000000"))
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var editText: EditText? = null

        init {
            title = itemView.rw_tv
            editText = itemView.rv_ed
        }
    }
}