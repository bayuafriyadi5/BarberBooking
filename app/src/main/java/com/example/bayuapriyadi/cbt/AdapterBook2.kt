package com.example.bayuapriyadi.cbt


import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase



class AdapterBook2 (val mCtx: Context, val layoutResIdDate: Int, val tanggallist: MutableList<WaktuBook>)
    :ArrayAdapter<WaktuBook>(mCtx,layoutResIdDate,tanggallist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val  view : View = layoutInflater.inflate(layoutResIdDate, null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textKategori = view.findViewById<TextView>(R.id.textKategori)
        val textTanggal = view.findViewById<TextView>(R.id.textTanggal)
        val textWaktu = view.findViewById<TextView>(R.id.textWaktu)
        val textHp = view.findViewById<TextView>(R.id.textHp)


        val textUpdate = view.findViewById<TextView>(R.id.textUpdate)
        val textDelete = view.findViewById<TextView>(R.id.textDelete)

        val book = tanggallist[position]

        textNama.text = book.nama
        textKategori.text = book.kategori
        textTanggal.text = book.tanggal
        textWaktu.text = book.waktu
        textHp.text = book.hp

        textUpdate.setOnClickListener {
            showUpdateDialog(book)
        }
        textDelete.setOnClickListener {
            Deleteinfo(book)
        }

        return view;
    }

    private fun Deleteinfo(book: WaktuBook) {
        val progressDialog = ProgressDialog(context,
                R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val userId = FirebaseAuth.getInstance().getCurrentUser()!!.getUid()
        val mydatabase = FirebaseDatabase.getInstance().getReference("Booking")
        mydatabase.child(userId).child(book.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Transaksi::class.java)
        context.startActivity(intent)
    }

    private fun showUpdateDialog(book: WaktuBook) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update Transaction")

        val inflater = LayoutInflater.from(mCtx)

        val view =  inflater.inflate(R.layout.layout_update_transaksi, null)

        val textNama = view.findViewById<EditText>(R.id.inputNama)
        val textKategori = view.findViewById<EditText>(R.id.inputKategori)
        val textTanggal = view.findViewById<EditText>(R.id.inputTanggal)
        val textWaktu = view.findViewById<EditText>(R.id.inputWaktu)
        val textHp = view.findViewById<EditText>(R.id.inputHp)
        val userId = FirebaseAuth.getInstance().getCurrentUser()!!.getUid()
        textNama.setText(book.nama)
        textKategori.setText(book.kategori)
        textTanggal.setText(book.tanggal)
        textWaktu.setText(book.waktu)
        textHp.setText(book.hp)


        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->
            val dbBooking = FirebaseDatabase.getInstance().getReference("Booking")

            val nama = textNama.text.toString().trim()
            val kategori = textKategori.text.toString().trim()
            val tanggal = textTanggal.text.toString().trim()
            val waktu = textWaktu.text.toString().trim()
            val hp = textHp.text.toString().trim()

            if (nama.isEmpty()){
                textNama.error = "please enter name"
                textNama.requestFocus()
                return@setPositiveButton
            }
            if (kategori.isEmpty()){
                textKategori.error = "please enter name"
                textKategori.requestFocus()
                return@setPositiveButton
            }
            if (tanggal.isEmpty()){
                textTanggal.error = "please enter name"
                textTanggal.requestFocus()
                return@setPositiveButton
            }
            if (waktu.isEmpty()){
                textWaktu.error = "please enter name"
                textWaktu.requestFocus()
                return@setPositiveButton
            }
            if (hp.isEmpty()){
                textHp.error = "please enter name"
                textHp.requestFocus()
                return@setPositiveButton
            }
            val progressDialog = ProgressDialog(context,
                    R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Updating...")
            progressDialog.show()
            val book = WaktuBook(book.id,nama,kategori,tanggal,waktu,hp)

            dbBooking.child(userId).child(book.id).setValue(book).addOnCompleteListener {
            Toast.makeText(mCtx,"Book Update",Toast.LENGTH_SHORT).show()
            }
            progressDialog.hide()

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create();
        alert.show()
    }
}
