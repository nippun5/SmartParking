package com.example.smartparking.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R
import com.example.smartparking.base.User


class RecyclerAdapter(private val context:Context, private val arrayList: ArrayList<User>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text=arrayList[position].toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
     var itemTitle: TextView
    init {
        itemTitle = itemView.findViewById(R.id.parkingName)
        itemView.setOnClickListener{
            Toast.makeText(itemView.context, "Item Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

}