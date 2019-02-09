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
import kotlinx.android.synthetic.main.activity_sign_up.*

@Suppress("UNREACHABLE_CODE")
class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        SignUpbtndalem.setOnClickListener {
            val email = emailSignUp.text.toString()
            val password = passwordSignUp.text.toString()

            Log.d("SignUp", "Email is" + email)
            Log.d("SignUp", "Password:$password")

            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val progressDialog = ProgressDialog(this,
                    R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Authenticating...")
            progressDialog.show()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{
                        if (!it.isSuccessful){ return@addOnCompleteListener
                        val intent = Intent (this, SignUp::class.java)
                            startActivity(intent)
                        }
                        else
                            Log.d("Main", "Succesfully created user with uid: ${it.result.user.uid}")
                        Toast.makeText(this, "Succesfully Create an Account", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this, SignIn::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener{
                        Log.d("Main", "Failed to create Account: ${it.message}")
                        Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                    }
        }
        Alreadybtn.setOnClickListener {
            val intent = Intent (this, com.example.bayuapriyadi.cbt.SignIn::class.java)
            startActivity(intent)
        }
    }
}
