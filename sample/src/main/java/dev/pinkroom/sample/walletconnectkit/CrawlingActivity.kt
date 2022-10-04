package dev.pinkroom.sample.walletconnectkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_crawling.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.Exception

class CrawlingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawling)

        // 크롤링 시작
        btnStart.setOnClickListener {
            // 리사이클러 뷰 매니저 설정
            listView.layoutManager = LinearLayoutManager(this)

            // MainActivity 로부터 addTxt 변수 전달 받아 etherCode로 저장
            var tmp = intent.getStringExtra("addTxt").toString()
            val etherCode = tmp.substring(16)
            Toast.makeText(this@CrawlingActivity,"https://opensea.io/" + etherCode,Toast.LENGTH_SHORT).show()

            //크롤링 함수 실행
            doTask("https://opensea.io/" + etherCode)

        }
//        listView.layoutManager = LinearLayoutManager(this)
//
//        // MainActivity 로부터 addTxt 변수 전달 받아 etherCode로 저장
//        var tmp = intent.getStringExtra("addTxt").toString()
//        val etherCode = tmp.substring(16)
//        Toast.makeText(this@CrawlingActivity, "https://opensea.io/" + etherCode, Toast.LENGTH_SHORT)
//            .show()
//
//        //크롤링 함수 실행
//        doTask("https://opensea.io/" + etherCode)
    }

    // 크롤링 하기
    fun doTask(url: String) {
        var documentTitle: String = ""
        var itemList: ArrayList<CrawlingItem> = arrayListOf()


        //single.fromcallagble 실행안되는 오류 발생
        Single.fromCallable {
            try {
                // 사이트에 접속해서 HTML 문서 가져오기
                var connection = Jsoup.connect(url)
                connection.userAgent("Chrome/105.0")
                var doc = connection.get()
                //Log.d("Tssssss", doc.toString())
                // HTML 파싱해서 데이터 추출하기
                val elements : Elements = doc.select("div.sc-cc7de838-0 span img")

                /*
                * 여러번 해봤는데 elements 에서 select 기능이 안되는듯
                * 전체 소스코드는 잘 받아오는데 맨처음 parsing이 안되서
                * 그다음 부분으로 넘어가서 리스트를 받지를 못함
                * doc.select 부분을 손봐야 할 것 같음
                * */

                //Log.d("Tssssss", elements.toString())
                // (여러개의) elements 처리
                for (e in elements) {
                    var title = e.absUrl("alt")
                    var image = e.absUrl("src")
                    title = title.replace("https://opensea.io/", "")

                    if (image.contains("https")) {
                        var item = CrawlingItem(title, image)
                        itemList.add(item)
                        Log.d("TAAAAB", title.toString())
                        Log.d("TAAAB", image.toString())
                    }
                }


//                run elemLoop@{
//                    elements.forEachIndexed{ index, elem ->
//                        // elem은 하나의 li를 전달해줌
//                        var title = elem.select("div.sc-6990c3a-0").text()
//                        var image = elem.select("div.sc-cc7de838-0 span img").attr("src")
//
////                        Log.d("TAAAAB", title.toString())
//                        Log.d("TAAAB", image.toString())
//                        // MovieItem 아이템 생성 후 추가
//
//                        if (image.contains("https")) {
//                            var item = CrawlingItem(title, image)
//                            itemList.add(item)
//                        }
//
//                        // 10개만 가져오기
//                        if (index == 9) return@elemLoop
//                    }
//                }

                // 올바르게 HTMl 문서 가져왔다면 title로 바꾸기
                documentTitle = doc.title()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return@fromCallable documentTitle   // subscribe 호출
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // documentTitle 응답 성공 시
                { text ->
                    // 리사이클러뷰에 아이템 연결
                    listView.adapter = CrawlingAdapter(itemList)
                },
                // documentTitle 응답 오류 시
                { it.printStackTrace() })

    }
}