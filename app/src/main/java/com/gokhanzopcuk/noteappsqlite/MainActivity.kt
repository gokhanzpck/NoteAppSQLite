package com.gokhanzopcuk.noteappsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokhanzopcuk.noteappsqlite.adapter.NoteAdapter
import com.gokhanzopcuk.noteappsqlite.clas.Note
import com.gokhanzopcuk.noteappsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteList:ArrayList<Note>
    private lateinit var noteAdapter:NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        noteList=ArrayList<Note>()

        noteAdapter= NoteAdapter(noteList)
        binding.recylerView.layoutManager=LinearLayoutManager(this)
        binding.recylerView.adapter=noteAdapter
      try {
          val database=this.openOrCreateDatabase("Note", MODE_PRIVATE,null)
          val cursor=database.rawQuery("SELECT * FROM note",null)
          val baslikIx=cursor.getColumnIndex("baslik")
          val idIx=cursor.getColumnIndex("id")
          while (cursor.moveToNext()){
              val baslik=cursor.getString(baslikIx)
              val id=cursor.getInt(idIx)
              val not=Note(id,baslik)
              noteList.add(not)
              println("a4")

          }
          cursor.close()
      }catch (e:Exception){
          e.printStackTrace()}

       binding.button.setOnClickListener {
          val intent=Intent(this@MainActivity,AddNoteActivity::class.java)
          intent.putExtra("info","new")
          startActivity(intent)



}
    }
}