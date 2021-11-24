package com.example.notesapp.datos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.Fecha

@Entity(tableName = "note_table")
data class Nota (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    var titulo : String,
    var descripcion : String,
    var esTarea: Boolean,
    var fechaLimite: String
);