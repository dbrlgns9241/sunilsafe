package dev.pinkroom.sample.walletconnectkit.NFT

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinkroom.sample.walletconnectkit.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_crawling.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.Exception

class CrawlingActivity : AppCompatActivity() {

    var itemList: ArrayList<CrawlingItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawling)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        // 크롤링 시작
//        btnStart.setOnClickListener {
//            // 리사이클러 뷰 매니저 설정
//            listView.layoutManager = LinearLayoutManager(this)
//
//            // MainActivity 로부터 addTxt 변수 전달 받아 etherCode로 저장
//            var tmp = intent.getStringExtra("addTxt").toString()
//            val etherCode = tmp.substring(16)
//            Toast.makeText(this@CrawlingActivity,"https://opensea.io/" + etherCode,Toast.LENGTH_SHORT).show()
//
//            //크롤링 함수 실행
//            doTask("https://opensea.io/" + etherCode)
//
//        }
        listView.layoutManager = LinearLayoutManager(this)

        // MainActivity 로부터 addTxt 변수 전달 받아 etherCode로 저장
        var tmp = intent.getStringExtra("addTxt").toString()
        val etherCode = tmp.substring(16)
        Toast.makeText(this@CrawlingActivity, "https://opensea.io/" + etherCode, Toast.LENGTH_SHORT)
            .show()

        //크롤링 함수 실행
        doTask("https://opensea.io/" + etherCode)


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
                val elements : Elements = doc.select("article.sc-82fdd4b8-6 a")
//                val elements2 : Elements = doc.select("div.sc-f0b2142c-0 span img")

//                Log.d("Tssssss", elements.toString())
                // (여러개의) elements 처리
                for (e in elements) {
                    Log.d("Tssssss", e.toString())
                    var etherURL = e.absUrl("href")

//                    var connection = Jsoup.connect(etherURL)
//                    connection.userAgent("Chrome/105.0")
//                    var doc = connection.get()
                    //Log.d("Tssssss", doc.toString())
                    // HTML 파싱해서 데이터 추출하기
                    val elementName= e.select("div.sc-f087f95e-0 span img").attr("alt")
                    val elementImage = e.select("div.sc-f087f95e-0 span img").attr("srcset")
//                    Log.d("Taaaab", elementName.toString())
//                    Log.d("Taaaac", elementImage.toString())
//                        doc.select("div.sc-45b283d7-0 div div span img").attr("src")//src
                    var ether1 = etherURL.split("/assets/")
                    var ether2 = ether1[1].split("/0x")
                    var ether = ether2[0]
//                    Log.d("Taaaad", ether.toString())

//                    val ether : String = doc.select("div.TradeStation--price-container div div").toString()
                    var title = elementName
                    var image1 = elementImage.split(" ")
                    var image = image1[0]
//                    title = title.replace("https://opensea.io/", "")

                    if (image.contains("https")) {
                        var item = CrawlingItem(title, image, ether)
                        itemList.add(item)
                        Log.d("TAAAAB", title.toString())
                        Log.d("TAAAB", image.toString())
                        Log.d("TAAABbbb", ether.toString())
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