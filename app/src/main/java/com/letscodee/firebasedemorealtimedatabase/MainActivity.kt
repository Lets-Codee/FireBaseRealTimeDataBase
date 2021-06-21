package com.letscodee.firebasedemorealtimedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.data.DataHolder
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uuidBtn=findViewById<Button>(R.id.uuidBtn)
        val sqBtn=findViewById<Button>(R.id.seqBtn)

        val name=findViewById<EditText>(R.id.name)
        val email=findViewById<EditText>(R.id.email)
        val number=findViewById<EditText>(R.id.number)
        val message=findViewById<EditText>(R.id.message)
        val learn=findViewById<EditText>(R.id.learn)

        uuidBtn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("uniqueId")

            val id=UUID.randomUUID().toString()
            val firebaseDataObj=firebaseData(name.text.toString(),email.text.toString(),number.text.toString(),message.text.toString(),learn.text.toString())
            myRef.child(id).setValue(firebaseDataObj)
            startActivity(Intent(this,MainActivity::class.java))
        }

        sqBtn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("sequenceId")

            myRef.get().addOnSuccessListener {
                val firebaseDataObj=firebaseData(name.text.toString(),email.text.toString(),number.text.toString(),message.text.toString(),learn.text.toString())
                myRef.child((it.childrenCount+1).toString()).setValue(firebaseDataObj)
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}