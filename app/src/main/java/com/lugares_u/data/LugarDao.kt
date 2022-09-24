package com.lugares_u.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lugares_u.model.Lugar

@Dao
interface LugarDao {
    @Query(value = "SELECT * FROM LUGAR")
    fun getAllData() : LiveData<List<Lugar>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  addLugar(Lugar : Lugar)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  updateLugar(lugar: Lugar)
    @Delete
    suspend fun deleteLugar(lugar: Lugar)
}