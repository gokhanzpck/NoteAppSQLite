package com.gokhanzopcuk.noteappsqlite.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gokhanzopcuk.noteappsqlite.AddNoteActivity
import com.gokhanzopcuk.noteappsqlite.MainActivity
import com.gokhanzopcuk.noteappsqlite.clas.Note
import com.gokhanzopcuk.noteappsqlite.databinding.ActivityAddNoteBinding
import com.gokhanzopcuk.noteappsqlite.databinding.RecylerRowBinding

class NoteAdapter(val noteList:ArrayList<Note>):RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    class NoteHolder(val binding: RecylerRowBinding):RecyclerView.ViewHolder(binding.root){



    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
     val binding=RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteHolder(binding)
    }

    override fun getItemCount(): Int {
return noteList.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
       holder.binding.recyclerViewTextView.text=noteList.get(position).baslik
   holder.itemView.setOnClickListener {
       val intent=Intent(holder.itemView.context,AddNoteActivity::class.java)
       intent.putExtra("info","old")
       intent.putExtra("id",noteList.get(position).note_id)
       intent.putExtra("baslik",noteList.get(position).baslik)
       holder.itemView.context.startActivity(intent)
   }

    }


}
