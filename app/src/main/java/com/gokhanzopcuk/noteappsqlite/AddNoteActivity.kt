package com.gokhanzopcuk.noteappsqlite

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.gokhanzopcuk.noteappsqlite.databinding.ActivityAddNoteBinding
import com.gokhanzopcuk.noteappsqlite.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var database:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent=intent
        val info=intent.getStringExtra("info")
        val database=this.openOrCreateDatabase("Note",Context.MODE_PRIVATE,null)
        val selectedId=intent.getIntExtra("id",1)
        val cursor=database.rawQuery("SELECT * FROM note WHERE id= ?", arrayOf(selectedId.toString()))
        binding.saveButton.setOnClickListener {
            val baslik=binding.baslikText.text.toString()
            val aciklama=binding.aciklamaText.text.toString()
            database.execSQL("CREATE TABLE IF NOT EXISTS note( id INTEGER PRIMARY KEY ,baslik VARCHAR,aciklama VARCHAR)")
            try {


                val sqlString="INSERT INTO note(baslik,aciklama) VALUES (?,?)"
                val statement=database.compileStatement(sqlString)
                statement.bindString(1,baslik)
                statement.bindString(2,aciklama)
                statement.execute()
                println("a")
            }catch (e:Exception){
                e.printStackTrace()
            }
            val intent=Intent(this@AddNoteActivity,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        if (info!=null){
        if (info.equals("new")){
             binding.baslikText.setText("")
            binding.aciklamaText.setText("")
            binding.saveButton.visibility=View.VISIBLE
            binding.buttonDelete.visibility=View.INVISIBLE
            println("a1")

        }else{println("a2")
          binding.saveButton.visibility=View.INVISIBLE
            binding.buttonDelete.visibility=View.VISIBLE

            println("a22")
            val baslikIx=cursor.getColumnIndex("baslik")
            val aciklamaIx=cursor.getColumnIndex("aciklama")
            println("a23")
            while (cursor.moveToNext()){
            println("a25")
            binding.baslikText.setText(cursor.getString(baslikIx))
            binding.aciklamaText.setText(cursor.getString(aciklamaIx))
            println("a24")
        }
            cursor.close()
        }
            binding.buttonDelete.setOnClickListener {

                database.execSQL("DELETE FROM note WHERE id=${selectedId} ")

                val intent=Intent(this@AddNoteActivity,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        }
    }

       }


