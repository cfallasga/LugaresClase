package com.lugares_u.data

import androidx.room.Room
import androidx.room.RoomDatabase

abstract  class LugarDatabase : RoomDatabase() {
    abstract fun lugarDao() : LugarDao

    companion object{
        @Volatile
        private var INSTANCE : LugarDatabase? = null

        fun getDatabase(context : android.content.Context) : LugarDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDatabase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}