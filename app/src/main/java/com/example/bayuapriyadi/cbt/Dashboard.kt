package com.example.bayuapriyadi.cbt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.signin.SignIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        LogOutbtn.setOnClickListener{
            mAuth!!.signOut()
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Log Out Succesfully", Toast.LENGTH_SHORT).show()
        }
        Bookbtn.setOnClickListener{
            val intent = Intent (this, Book2::class.java)
            startActivity(intent)
        }
        Transactionbtn.setOnClickListener {
            val intent = Intent(this, Transaksi::class.java)
            startActivity(intent)
        }
    }
}
