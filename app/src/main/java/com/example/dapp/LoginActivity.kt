package com.example.dapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dapp.databinding.ActivityLoginBinding
import android.content.Intent
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth

        val view = binding.root
        setContentView(view)

        var email = binding.etEmail
        var pw = binding.etPass

        if(auth.currentUser?.uid == null){
            Log.d("LoginActivity", "null")
        }else{
            Log.d("LoginActivity", "not null")
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnLogin.setOnClickListener{ // 로그인 버튼 클릭
            auth.signInWithEmailAndPassword(
                email.text.toString(),
                pw.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
               //         Toast.makeText(baseContext, "로그인 완료!", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.btnRegister.setOnClickListener{ // 회원가입 버튼 클릭
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
