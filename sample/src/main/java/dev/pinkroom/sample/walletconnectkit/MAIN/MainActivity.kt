package dev.pinkroom.sample.walletconnectkit.MAIN

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dev.pinkroom.sample.walletconnectkit.FRAGMENT.HomeFragment
import dev.pinkroom.sample.walletconnectkit.LOGIN.JoinActivity
import dev.pinkroom.sample.walletconnectkit.LOGIN.LoginActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY.BrandActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY.EnterpriseActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY.GreenActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.INSTACTIVITY.InjaeActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.SAFEACTIVITY.SafeActivity
import dev.pinkroom.sample.walletconnectkit.MAIN.TECHACTIVITY.*
import dev.pinkroom.sample.walletconnectkit.NFT.NFTActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dev.pinkroom.sample.walletconnectkit.R.layout.activity_main)

        val toolbar: Toolbar = findViewById(dev.pinkroom.sample.walletconnectkit.R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(dev.pinkroom.sample.walletconnectkit.R.drawable.ic_baseline_dehaze_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(dev.pinkroom.sample.walletconnectkit.R.id.drawer_layout)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(dev.pinkroom.sample.walletconnectkit.R.id.nv_drawer)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너

        // 초기 프래그먼트 셋팅
        val fragment = HomeFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(dev.pinkroom.sample.walletconnectkit.R.id.frame_layout, fragment, "Home")
        fragmentTransaction.commit()
        
        // expandableList 실행
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
        val parentList = mutableListOf("로그인","회사소개", "테크놀러지", "제품소개","NFT 갤러리")
        val childList = mutableListOf(
            mutableListOf("회원가입","로그인"),
            mutableListOf("기업소개","브랜드 소개", "그린 경영", "인재채용"),
            mutableListOf("KT GIGA EYES 금고 CAM", "IOT금고","인증기술","잠금장치","화재보호","안전기술","내화테스트","방도테스트"),
            mutableListOf(),
            mutableListOf()
        )

        val expandableAdapter = ExpandableListAdapter(this, parentList, childList)
        el_menu.setAdapter(expandableAdapter)

        /* parent 클릭 이벤트 설정 */
        el_menu.setOnGroupClickListener { parent, v, groupPosition, id ->
            when (groupPosition) {
                //제품소개
                3 -> {
                    val intent = Intent(this, SafeActivity::class.java)
                    startActivity(intent)
                }
                //NFT 갤러리
                4 -> {
                    val intent = Intent(this, NFTActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        /* child 클릭 이벤트 설정 */
        el_menu.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            when (groupPosition) {
                //로그인
                0-> when(childPosition){
                    //회원가입
                    0 -> {
                        val intent = Intent(this, JoinActivity::class.java)
                        startActivity(intent)
                    }
                    //로그인
                    1 -> {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
                //회사소개
                1 -> when (childPosition ){
                    //기업소개
                    0 -> {
                        val intent = Intent(this, EnterpriseActivity::class.java)
                        startActivity(intent)
                    }
                    //브랜드소개
                    1 -> {
                        val intent = Intent(this, BrandActivity::class.java)
                        startActivity(intent)
                    }
                    //그린경영
                    2 -> {
                        val intent = Intent(this, GreenActivity::class.java)
                        startActivity(intent)
                    }
                    //인재채용
                    3 -> {
                        val intent = Intent(this, InjaeActivity::class.java)
                        startActivity(intent)
                    }
                }
                //테크놀러지
                2 -> when(childPosition){
                    //KT GIGA EYES 금고 cam
                    0 -> {
                        val intent = Intent(this, KtActivity::class.java)
                        startActivity(intent)
                    }
                    //IOT 금고
                    1 -> {
                        val intent = Intent(this, IotActivity::class.java)
                        startActivity(intent)
                    }
                    //인증기술
                    2 -> {
                        val intent = Intent(this, CertiActivity::class.java)
                        startActivity(intent)
                    }
                    //잠금장치
                    3 -> {
                        val intent = Intent(this, LockActivity::class.java)
                        startActivity(intent)
                    }
                    //화재보호
                    4 -> {
                        val intent = Intent(this, FireActivity::class.java)
                        startActivity(intent)
                    }
                    //안전기술
                    5 -> {
                        val intent = Intent(this, SafetechActivity::class.java)
                        startActivity(intent)
                    }
                    //내화테스트
                    6 -> {
                        val intent = Intent(this, NaehwaActivity::class.java)
                        startActivity(intent)
                    }
                    //방도테스트
                    7 -> {
                        val intent = Intent(this, BangdoActivity::class.java)
                        startActivity(intent)
                    }
                }
                //제품소개
                3 -> {}
                //NFT 갤러리
                4 -> {}
            }
            false
        }

    }

}

