@file:Suppress("UNREACHABLE_CODE")

package com.example.bayuapriyadi.cbt

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.signin.SignIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        SignInbtndalem.setOnClickListener {
            val emailLogin = Email_login.text.toString()
            val passwordLogin = Password_login.text.toString()

            if (emailLogin.isEmpty()|| passwordLogin.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val progressDialog = ProgressDialog(this,
                    R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Authenticating...")
            progressDialog.show()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLogin,passwordLogin)

                    .addOnCompleteListener{

                        if (!it.isSuccessful){ return@addOnCompleteListener
                            val intent = Intent (this, SignIn::class.java)
                            startActivity(intent)
                        }
                        else
                        Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener{
                        Log.d("Main", "Failed Login: ${it.message}")
                        Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                        progressDialog.hide()

                    }
        }
    }
}