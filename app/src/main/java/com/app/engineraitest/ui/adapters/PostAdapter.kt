package com.app.engineraitest.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.engineraitest.R
import com.app.engineraitest.data.ClsPost
import com.app.engineraitest.interfaces.SetOnCheckChangeListener

class PostAdapter(private var activity: Activity, private var hits: ArrayList<ClsPost.Hit> ,  private var setOnCheckChangeListener: SetOnCheckChangeListener) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_post, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return hits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val hit = hits[position]

        if (hit.title != null)
            holder.txtTitle.text = hit.title

        if (hit.url != null)
            holder.txtUrl.text = hit.url

        holder.imgSelection.setImageResource(if(hit.selected) android.R.drawable.checkbox_on_background else android.R.drawable.checkbox_off_background)

        holder.imgSelection.setOnClickListener {
            hit.selected = !hit.selected
            holder.imgSelection.setImageResource(if(hit.selected) android.R.drawable.checkbox_on_background else android.R.drawable.checkbox_off_background)
            setOnCheckChangeListener.onCheckChangeListener(hit.selected)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        var txtUrl: TextView = itemView.findViewById(R.id.txtUrl)
        var imgSelection: ImageView = itemView.findViewById(R.id.imgSelection)

    }
}

private fun ViewGroup.inflate(recyclerviewItemRow: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(recyclerviewItemRow, this, attachToRoot)
}
