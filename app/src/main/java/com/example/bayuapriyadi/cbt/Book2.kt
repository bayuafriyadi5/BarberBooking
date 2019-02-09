package com.example.bayuapriyadi.cbt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_book2.*
import java.text.SimpleDateFormat
import java.util.*

class Book2 : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book2)

        ref = FirebaseDatabase.getInstance().getReference("Booking")

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        PickDatebtn.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                dateTv.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
            }, year, month, day)

            dpd.show()
        }

        PickTimebtn.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)

                timeTv.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }
        Book2Next.setOnClickListener {
            val progressDialog = ProgressDialog(this,
                    R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Prossessss...")
            progressDialog.show()
            val intent = Intent(this, Slider::class.java)
            startActivity(intent)
            Toast.makeText(this, "Book Succesful", Toast.LENGTH_SHORT).show()
        }
        SaveDatebtn.setOnClickListener {
            savedate()
        }

    }

     fun savedate() {
            val nama = inputNama.text.toString()
            val kategori = inputKategori.text.toString()
            val tanggal = dateTv.text.toString()
            val waktu = timeTv.text.toString()
            val hp = inputHp.text.toString()

            val userId = FirebaseAuth.getInstance().getCurrentUser()!!.getUid()
            val waktuId = ref.push().key.toString()
            val waktubook = WaktuBook(waktuId,nama,kategori,tanggal,waktu,hp)


         val progressDialog = ProgressDialog(this,
                 R.style.Theme_MaterialComponents_Light_Dialog)
         progressDialog.isIndeterminate = true
         progressDialog.setMessage("Prossessss...")
         progressDialog.show()

        ref.child(userId).child(waktuId).setValue(waktubook).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            progressDialog.hide()
        }
    }
}

