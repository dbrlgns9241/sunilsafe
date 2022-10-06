package dev.pinkroom.sample.walletconnectkit.MAIN

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dev.pinkroom.sample.walletconnectkit.NFT.NFTActivity
import dev.pinkroom.sample.walletconnectkit.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(R.id.drawer_layout)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(R.id.nv_drawer)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너

        setExpandableList()
    }

    // 툴바 메뉴 버튼이 클릭 됐을 때 실행하는 함수
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 클릭한 툴바 메뉴 아이템 id 마다 다르게 실행하도록 설정
        when (item!!.itemId) {
            android.R.id.home -> {
                // 햄버거 버튼 클릭시 네비게이션 드로어 열기
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 드로어 내 아이템 클릭 이벤트 처리하는 함수 없으면 에러남
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

    /* ExpandableListView 설정 */
    private fun setExpandableList() {
        val parentList = mutableListOf("로그인","회사소개", "제품소개", "테크놀러지","홍보센터","고객지원","NFT 갤러리")
        val childList = mutableListOf(
            mutableListOf("회원가입","로그인", "마이페이지", "장바구니"),
            mutableListOf("기업소개","브랜드 소개", "그린 경영", "인재채용"),
            mutableListOf("나만의 금고찾기", "가정용 인테리어 금고","사무용 내화 금고","내화 방도 겸용 금고","특수금고"),
            mutableListOf("KT GIGA EYES 금고 CAM", "IOT금고","인증기술","잠금장치","화재보호","안전기술","내화테스트","방도테스트") ,
            mutableListOf("뉴스&공지사항","보도자료","이벤트","홍보, 동영상"),
            mutableListOf("고객지원","자주 묻는 질문(A.S안내)","제품 설명서","우리지역 판매점"),
            mutableListOf("나의 NFT 갤러리 가기")
        )

        val expandableAdapter = ExpandableListAdapter(this, parentList, childList)
        el_menu.setAdapter(expandableAdapter)

        el_menu.setOnGroupClickListener { parent, v, groupPosition, id ->
            /* todo : parent 클릭 이벤트 설정 */
            when(groupPosition){
                5 ->{
                    val intent = Intent(this, NFTActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        el_menu.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            /* todo : child 클릭 이벤트 설정 */
            when(groupPosition){
                0 -> {}
                1 -> when(childPosition) {
                    0 -> Toast.makeText(this, "기업소개", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this, "브랜드 소개", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this, "그린 경영", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(this, "인재채용", Toast.LENGTH_SHORT).show()
                }
                2 -> {}
                3 -> {}
                4 -> {}
                5 -> {}
                6 -> when(childPosition){
                    0 -> {
                        val intent = Intent(this, NFTActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            false
        }
    }

}