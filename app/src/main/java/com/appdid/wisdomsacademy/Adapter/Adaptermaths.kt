package com.appdid.wisdomsacademy.Adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.appdid.wisdomsacademy.Model.Pojo
import com.appdid.wisdomsacademy.R
import kotlinx.android.synthetic.main.content_main_page.view.*

import java.util.ArrayList


class Adaptermaths(var context: Context, list: ArrayList<Pojo>) : RecyclerView.Adapter<Adaptermaths.Myviewholder>() {

    var listmaths = ArrayList<Pojo>()

    init
    {
        this.listmaths = list

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Myviewholder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.ssctopperlist,
            viewGroup, false)
        return Myviewholder(itemView)
    }

    override fun onBindViewHolder(myviewholder: Myviewholder, i: Int) {

        val pojo = listmaths[i]

        myviewholder.imageView.setBackgroundResource(pojo.image)
        myviewholder.txtname.text = pojo.name
        myviewholder.txtpercentage.text = (pojo.percentage)

    }

    override fun getItemCount(): Int {
        return listmaths.size
    }

    inner class Myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtname: TextView
        var txtpercentage: TextView
        var imageView: ImageView

        init
        {
            imageView = itemView.findViewById(R.id.imageview)
            txtname = itemView.findViewById(R.id.txtname)
            txtpercentage = itemView.findViewById(R.id.txtpercentage)

        }
    }
}
