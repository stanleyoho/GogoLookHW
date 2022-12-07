package com.gogolook.hw.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gogolook.hw.app.R
import com.gogolook.hw.app.data.ApiGetImageModel
import com.gogolook.hw.app.enums.LayoutTypeEnum
import kotlinx.android.synthetic.main.recycler_grid_layout.view.*

class RecyclerViewAdapter(
    private val context: Context,
    private val layoutType: LayoutTypeEnum,
    var data: ApiGetImageModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (layoutType == LayoutTypeEnum.LIST_LAYOUT) {
            ListViewVH(
                LayoutInflater.from(context)
                    .inflate(R.layout.recycler_list_layout, parent, false)
            )
        } else {
            GridViewVH(
                LayoutInflater.from(context)
                    .inflate(R.layout.recycler_grid_layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data.hits[position]
        if (layoutType == LayoutTypeEnum.LIST_LAYOUT) {
            val myHolder = holder as ListViewVH
            Glide.with(context)
                .load(item.webformatURL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(myHolder.image)

        } else {
            val myHolder = holder as GridViewVH
            Glide.with(context)
                .load(item.webformatURL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(myHolder.image)
        }
    }

    override fun getItemCount(): Int {
        return data.hits.size
    }

    fun updateData(imageData: ApiGetImageModel) {
        data = imageData
        notifyDataSetChanged()
    }

    class ListViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.iv
    }

    class GridViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.iv
    }
}