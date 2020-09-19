package ru.ppr.ppr.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_date_view_holder.view.*
import ru.ppr.ppr.R
import ru.ppr.ppr.view.SyntheticViewHolder

class MainAdapter : RecyclerView.Adapter<SyntheticViewHolder>() {

    private var dates = mutableListOf<Long>()
    private var lastDatesSize = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyntheticViewHolder {
        return SyntheticViewHolder.inflateFrom(parent, R.layout.layout_date_view_holder)
    }

    override fun onBindViewHolder(holder: SyntheticViewHolder, position: Int) =
        with(holder.itemView) {
            if ((position - 1) % 4 == 0 || (position - 1) % 4 == 1) {
                dateBgLl.setBackgroundColor(resources.getColor(R.color.darkened))
            } else {
                dateBgLl.setBackgroundColor(resources.getColor(R.color.white))
            }
            dateTv.text = dates[position].toString()
        }

    override fun getItemCount(): Int = dates.size

    fun setDatesList(dates: MutableList<Long>) {
        this.dates = this.dates.plus(dates) as MutableList<Long>
        if (this.dates.size == 0) {
            notifyDataSetChanged()
            lastDatesSize = this.dates.size
        } else {
            notifyItemRangeChanged(lastDatesSize, dates.size)
            lastDatesSize += dates.size
        }

    }
}