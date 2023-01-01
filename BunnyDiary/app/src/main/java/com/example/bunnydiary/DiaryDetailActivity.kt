package com.example.bunnydiary

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bunnydiary.databinding.ActivityDiaryDetailBinding
import com.example.bunnydiary.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
//
/*
*상세보기 화면 or 작성하기 화면
*/

//class DiaryDetailActivity : AppCompatActivity(){}
class DiaryDetailActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var mTvDate: TextView //일시 설정 텍스트
    private lateinit var mEtTitle: EditText //일기 제목
    private lateinit var mEtContent: EditText //일기 내용
    private lateinit var mRgWeather: RadioGroup

    private var mDetailMode: String = "" // intent로 받아낸 게시글 모드
    private var mBeforeDate: String = "" // intent로 받아낸 게시글 기준 작성일자
    private var mSelectedUserDate: String = "" //선택된 날짜
    private var mSelectedWeatherType = -1 //선택된 날씨 값(0~5)

    private val sharedPreference by lazy{
        getSharedPreferences("record", Context.MODE_PRIVATE)
    }

    private val binding by lazy {
        ActivityDiaryDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mTvDate = binding.tvDate  //날짜 설정 텍스트
        mEtTitle = binding.etTitle //제목 입력 필드
        mEtContent = binding.etContent //내용 입력 필드
        mRgWeather = binding.rgWeather // 날씨 선택 라디오 그룹

        var ivBack: ImageView = binding.ivBack //뒤로가기 버튼
        var ivCheck: ImageView = binding.ivCheck //작성완료 버튼

        ivBack.setOnClickListener(this) //클릭 기능 부여
        ivCheck.setOnClickListener(this) //클릭 기능 부여
        mTvDate.setOnClickListener(this) //클릭 기능 부여

        //기본으로 설정될 날짜의 값을 지정(디바이스 현재 시간 기준)
        mSelectedUserDate = SimpleDateFormat("yyyy/MM/dd E요일", Locale.KOREA).format(Date())
        mTvDate.text = mSelectedUserDate

    }

    override fun onClick(view: View) {
        //setOnclickListener가 붙어있는 뷰들은 클릭이 발생하면 모두 이곳을 수행하게 됨
        var bool: Boolean = mEtTitle.text.isEmpty() || mEtContent.text.isEmpty()
        when (view.id) {
            //뒤로가기
            R.id.ivBack -> finish() //현재 액티비티 종료

            //작성 완료
            //라디오 그룹의 버튼 클릭 현재 상황 가져오기
            R.id.ivCheck -> {
                mSelectedWeatherType =
                    mRgWeather.indexOfChild(findViewById(mRgWeather.checkedRadioButtonId))
                //입력 필드 작성란이 비어있는지 확인
                if (bool) {
                    //error
                    Toast.makeText(this, "입력되지 않은 필드가 존재랍니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                //날씨 선택이 되어있는지 체크
                if (mSelectedWeatherType == -1) {
                    //error
                    Toast.makeText(this, "날씨를 선택해주세요.", Toast.LENGTH_SHORT).show()
                    return
                }
                //여기까지 도착했으면 에러 상황이 없으므로 데이터 저장

                with(sharedPreference.edit()){
                var title: String = mEtTitle.text.toString() // 제목 입력 값
                var content: String = mEtContent.text.toString() //내용 입력 값
                var userDate: String = mSelectedUserDate //사용자가 선택한 날짜

                var writeDate: String =
                    SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA).format(Date())
                //데이터베이스에 저장
                this.putString("title",title)
                this.putString("content",content)
                this.putString("date",userDate)
                finish()// 현재 액티비티 종료
            }}

            //일시 설정 텍스트
            R.id.tvDate -> {var calender:Calendar = Calendar.getInstance()
                val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    var innerCal:Calendar = Calendar.getInstance()
                    innerCal.set(Calendar.YEAR,year)
                    innerCal.set(Calendar.MONTH,month)
                    innerCal.set(Calendar.DAY_OF_MONTH, day)

                    mSelectedUserDate = SimpleDateFormat("yyyy/MM/dd E요일", Locale.KOREA).format(innerCal.time)
                    mTvDate.text = mSelectedUserDate
                }
                DatePickerDialog(this, dateSetListener, calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show()
            }

        }
    }

}

//interface OnDiaryClickListener{
//    fun onItemClick(holder:RecyclerView.ViewHolder, view: View, position:Int)
//}