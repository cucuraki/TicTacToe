package com.example.tictactoe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.databinding.SquareBinding

class Adapter(private val list: MutableList<Square>, private val listener: (Int) -> Unit ) : RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        SquareBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position].image)
        holder.image.layoutParams = ViewGroup.LayoutParams(list[position].height.toInt(), list[position].height.toInt())
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(private val binding: SquareBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.root
        init{
            image.setOnClickListener{
                listener(adapterPosition)
            }
        }
    }
}