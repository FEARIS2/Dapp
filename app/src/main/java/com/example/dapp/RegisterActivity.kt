package com.example.dapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth

        val view = binding.root
        setContentView(view)

        var pw = binding.signPW
        var email = binding.signmail
        var isPWSAME = false
        var empty = false

        binding.btnBack.setOnClickListener { // 뒤로 가기 버튼 클릭
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnPwchk.setOnClickListener {  // 패스워드 확인 버튼 클릭
            if (binding.signPW.text.toString() == binding.signPW2.text.toString()) { // pw1, pw2 비교
                Toast.makeText(this, "비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show()
                isPWSAME = true
            } else {
                Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
                isPWSAME = false
            }
        }

        binding.btnSignup.setOnClickListener { // 회원가입 버튼 클릭
            if (email.text.toString().isNotEmpty() || pw.text.toString().isNotEmpty()) {
                empty = true
            }

            if (isPWSAME && empty) {
                auth.createUserWithEmailAndPassword(email.text.toString(), pw.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(baseContext, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
            } else {
                Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
