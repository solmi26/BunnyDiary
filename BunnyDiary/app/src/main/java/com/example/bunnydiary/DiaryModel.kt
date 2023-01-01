package com.example.bunnydiary

data class DiaryModel(var id:Int, var title:String, var content:String, var weatherType:Int, var userDate:String, var writeDate:String):java.io.Serializable{
    constructor() : this(0, "", "", 0, "","")
}