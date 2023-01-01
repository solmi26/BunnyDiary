package com.example.bunnydiary

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class DiaryListAdapter:RecyclerView.Adapter<DiaryListAdapter.ViewHolder>() {
    lateinit var mLstDiary:ArrayList<DiaryModel> //다이어리 데이터들을 들고 있는 자료형 배열
    lateinit var mContext:Context
    lateinit var listener: OnDiaryClickListener

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        // 하나의 아이템 뷰
        init{
            itemView.setOnClickListener(View.OnClickListener {
                //현재 클릭이 된 위치(배열 개념이어서 첫 시작이 0부터 세는 기준)
                var currentPosition:Int = adapterPosition
                //현재 클릭 된 리스트 아이템 정보를 가지는 변수
                var diaryModel:DiaryModel = mLstDiary[currentPosition]

                //화면 이동 및 다이어리 데이터 다음 엑티비티로 전달
                var diaryDetailIntent:Intent = Intent(mContext,DiaryDetailActivity::class.java)
                diaryDetailIntent.putExtra("diaryModel",diaryModel) // 다이어리 데이터 넠기기
                diaryDetailIntent.putExtra("mode","detail") //상세보기 모드로 설정
                mContext.startActivity(diaryDetailIntent)

            })

            // 선택지 옵션 팝업(수정, 삭제)
            itemView.setOnLongClickListener(View.OnLongClickListener{
                //현재 클릭이 된 위치(배열 개념이어서 첫 시작이 0부터 세는 기준)
                var currentPosition:Int = adapterPosition
                //현재 클릭 된 리스트 아이템 정보를 가지는 변수
                var diaryModel:DiaryModel = mLstDiary[currentPosition]

                var strChoiceArray = arrayOf<String>("수정 하기", "삭제 하기")
                // 팝업화면 표시
                AlertDialog.Builder(mContext).setTitle("원하시는 동작을 선택하세요.").setItems(strChoiceArray,DialogInterface.OnClickListener{
                    dialogInterface, position ->  if (position==0){
                        //수정하기 버튼 눌렀을 때

                    //화면 이동 및 다이어리 데이터 다음 엑티비티로 전달
                    var diaryDetailIntent:Intent = Intent(mContext,DiaryDetailActivity::class.java)
                    diaryDetailIntent.putExtra("diaryModel",diaryModel) // 다이어리 데이터 넠기기
                    diaryDetailIntent.putExtra("mode","modify") //수정하기 모드로 설정
                    mContext.startActivity(diaryDetailIntent)
                    }else{
                        //삭제하기 버튼 눌렀을 때
                    mLstDiary.removeAt(currentPosition)
                    notifyItemRemoved(currentPosition)
                    }
                }).show()
                return@OnLongClickListener false
            })
        }


        val ivWeather:ImageView = itemView.findViewById(R.id.ivWeather) //날씨 이미지
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)     //다이어리 제목
        val tvUserDate:TextView = itemView.findViewById(R.id.tvUserDate) //사용자 지정 날짜

    }

//   inner class ViewHolder(itemView: View, listener:OnDiaryClickListener):RecyclerView.ViewHolder(itemView) {
//        // 하나의 아이템 뷰
//
//        init{
//            itemView.setOnClickListener(View.OnClickListener {
//                //현재 클릭이 된 위치(배열 개념이어서 첫 시작이 0부터 세는 기준)
//                var currentPosition:Int = adapterPosition
//                //현재 클릭 된 리스트 아이템 정보를 가지는 변수
//                var diaryModel:DiaryModel = mLstDiary[currentPosition]
//
//                //화면 이동 및 다이어리 데이터 다음 엑티비티로 전달
//                var diaryDetailIntent:Intent = Intent(mContext,DiaryDetailActivity::class.java)
//                diaryDetailIntent.putExtra("diaryModel",diaryModel) // 다이어리 데이터 넠기기
//                diaryDetailIntent.putExtra("mode","detail") //상세보기 모드로 설정
//                mContext.startActivity(diaryDetailIntent)
//
//            })
//
//            // 선택지 옵션 팝업(수정, 삭제)
//            itemView.setOnLongClickListener(View.OnLongClickListener{
//                return@OnLongClickListener false
//            })
//        }
//
//        val ivWeather:ImageView = itemView.findViewById(R.id.ivWeather) //날씨 이미지
//        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)     //다이어리 제목
//        val tvUserDate:TextView = itemView.findViewById(R.id.tvUserDate) //사용자 지정 날짜
//
//    }


    //오류
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.ViewHolder {
//        //아이템 뷰가 최초로 생성이 될 때 호출 되는 곳
//        mContext = parent.context
//        var holder:View = LayoutInflater.from(mContext).inflate(R.layout.list_item_diary,parent,false)
//        return ViewHolder(holder, listener)
//    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.ViewHolder {
        //아이템 뷰가 최초로 생성이 될 때 호출 되는 곳
        mContext = parent.context
        var holder:View = LayoutInflater.from(mContext).inflate(R.layout.list_item_diary,parent,false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: DiaryListAdapter.ViewHolder, position: Int) {
       // 생성된 아이템 뷰가 실제 연동이 되어지는 곳

        //날씨의 경우의 수 작성
        var weatherType:Int = mLstDiary[position].weatherType
        when(weatherType){
            //gm=
            0 -> holder.ivWeather.setImageResource(R.drawable.cloud);//흐림
            1 -> holder.ivWeather.setImageResource(R.drawable.wind);//바람
            2 -> holder.ivWeather.setImageResource(R.drawable.cloudy);//흐린 뒤 맑음
            3 -> holder.ivWeather.setImageResource(R.drawable.rainy);//비
            4 -> holder.ivWeather.setImageResource(R.drawable.snow);//눈
            5 -> holder.ivWeather.setImageResource(R.drawable.sun);//맑음
        }

        //날씨 제목, 날짜
        var title:String = mLstDiary[position].title
        var userDate:String = mLstDiary[position].userDate
        holder.tvTitle.text = title
        holder.tvUserDate.text = userDate
    }
    fun setOnItemClickListener(listener: OnDiaryClickListener){
        this.listener = listener
    }

    fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        listener?.onItemClick(holder,view,position)
    }


    override fun getItemCount(): Int {
        // 아이템 뷰의 총 갯수를 관리
        return mLstDiary.size
    }

    fun setSampleList(lstDiary:ArrayList<DiaryModel>){
        mLstDiary = lstDiary
    }


}