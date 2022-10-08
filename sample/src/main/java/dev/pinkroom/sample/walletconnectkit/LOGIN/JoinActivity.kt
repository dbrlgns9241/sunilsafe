package dev.pinkroom.sample.walletconnectkit.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.pinkroom.sample.walletconnectkit.MAIN.MainActivity
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    private var TAG = "JoinActivity"
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // toolBar를 통해 App Bar 생성
        setSupportActionBar(toolbar) // 툴바 적용
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("")

        auth = Firebase.auth

        login_btn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        join_btn.setOnClickListener{
            val email = findViewById<TextInputEditText>(R.id.emailArea)
            val pwd = findViewById<TextInputEditText>(R.id.pwdArea)
            val pwdcheck = findViewById<TextInputEditText>(R.id.pwdCheckArea)

            Log.d("tag", email.text.toString())
            Log.d("tag", pwd.text.toString())
            Log.d("tag", pwdcheck.text.toString())

            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    // 비밀번호 확인 동일한경우 성공
//                    if (task.isSuccessful && pwd.text.toString() == pwdcheck.text.toString()) {
                    if(task.isSuccessful){

                        val user = auth.currentUser
                        Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "회원가입이 실패하였습니다 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}