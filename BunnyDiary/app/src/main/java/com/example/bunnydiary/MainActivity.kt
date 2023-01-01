package com.example.bunnydiary

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bunnydiary.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var mRvDiary: RecyclerView
    lateinit var mAdapter:DiaryListAdapter
    lateinit var mLstDiary:ArrayList<DiaryModel>



    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mLstDiary = ArrayList()
        mRvDiary = binding.rvDiary
        mAdapter = DiaryListAdapter()

        // 다이어리 샘플 아이템 1개 생성
        var item:DiaryModel = DiaryModel()
        item.id = 0
        item.title = "솔미는 자고 싶어"
        item.content = "빨리 방학되서 늦잠자고 싶다ㅜ.ㅜ"
        item.userDate = "2022/11/30 Wed"
        item.weatherType = 1
        mLstDiary.add(item)

        // 다이어리 샘플 아이템 2개 생성
        var item2:DiaryModel = DiaryModel()
        item2.id = 0
        item2.title = "딸기 빙수 먹고 싶따"
        item2.content = "딸기~~~"
        item2.userDate = "2022/12/01 Thu"
        item2.weatherType = 2
        mLstDiary.add(item2)

        // 다이어리 샘플 아이템 3개 생성
        var item3:DiaryModel = DiaryModel()
        item3.id = 0
        item3.title = "코틀린 시험이군"
        item3.content = "껄껄"
        item3.userDate = "2022/12/02 Fri"
        item3.weatherType = 3
        mLstDiary.add(item3)

        mAdapter.setSampleList(mLstDiary)
        mRvDiary.adapter = mAdapter

        //액티비티 화면이 실행 될 때 가장 먼저 호출되는 곳
        var floatingActionButton:FloatingActionButton = binding.btnWrite
        floatingActionButton.setOnClickListener{ view: View ->
           var intent: Intent = Intent(this,DiaryDetailActivity::class.java)

        }
//        binding.btnWrite.setOnClickListener { view ->
//            Snackbar.make(view, "실제 클릭 이벤트를 대신 보여준다.", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
//        }
    }

}