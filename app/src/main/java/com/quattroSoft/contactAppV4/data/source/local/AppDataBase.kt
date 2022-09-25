package com.quattroSoft.contactAppV4.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.quattroSoft.contactAppV4.data.source.local.dao.ContactDao
import com.quattroSoft.contactAppV4.data.source.local.entity.ContactEntity


@Database(entities = [ContactEntity::class] , version = 1)
abstract class AppDataBase  : RoomDatabase(){

    abstract fun getContactDao()  : ContactDao

    companion object{

        private var instance :AppDataBase? = null

        fun init(context : Context) : AppDataBase {

            val temp = instance

            if (temp != null){
                return temp
            }

            val INSTANCE = Room.databaseBuilder(context, AppDataBase::class.java  ,"database.db")
                .allowMainThreadQueries()
                .build()

            instance = INSTANCE
            return instance as AppDataBase

        }









    }

}