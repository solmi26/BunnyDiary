package com.example.bunnydiary

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface OnDiaryClickListener {
    fun onItemClick(holder: RecyclerView.ViewHolder, view: View, position:Int)
}