package com.thecode.dagger_hilt_mvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }
}