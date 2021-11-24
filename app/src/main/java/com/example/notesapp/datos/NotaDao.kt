package com.example.notesapp.datos

import androidx.room.*

@Dao
interface NotaDao {
    @Insert
    fun addNota(nota : Nota)

    @Update
    fun updateNota(nota: Nota)

    @Delete
    fun deleteNota(nota: Nota)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getTodos():List<Nota>

    @Query("SELECT * FROM note_table where esTarea = 0 ORDER BY id ASC")
    fun getNotas(): List<Nota>

    @Query("SELECT * FROM note_table where esTarea = 1 ORDER BY id ASC")
    fun getTareas(): List<Nota>

    @Query("SELECT * FROM note_table where id = :id")
    fun getNota(id: Long): Nota

}