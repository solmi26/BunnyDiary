package com.example.bunnydiary.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyRecord(
    @PrimaryKey(autoGenerate = true) val title:String,
    @ColumnInfo val date:String,
    @ColumnInfo val weather:String,
    @ColumnInfo val content:String
)