package com.example.bayuapriyadi.cbt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Transaksi : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var tanggallist: MutableList<WaktuBook>
    lateinit var listtanggal : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)

        val userId = FirebaseAuth.getInstance().getCurrentUser()!!.getUid()

        tanggallist = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Booking").child(userId)
        listtanggal = findViewById(R.id.listtanggal)


        ref.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    tanggallist.clear()
                    for ( h in p0.children){
                        val waktu = h.getValue(WaktuBook::class.java)

                        tanggallist.add(waktu!!)

                    }
                    val adapter1 = AdapterBook2(this@Transaksi,R.layout.waktubook,tanggallist)

                    listtanggal.adapter = adapter1


                }
            }

        });
    }

}
