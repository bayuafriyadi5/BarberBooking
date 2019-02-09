package com.example.bayuapriyadi.cbt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignInbtndalem.setOnClickListener{
            val intent = Intent (this, SignIn::class.java)
            startActivity(intent)
        }
        SignUpbtndalem.setOnClickListener{
            val intent = Intent (this, SignUp::class.java)
            startActivity(intent)
        }
    }
}
