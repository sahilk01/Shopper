package com.example.shopper.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.db.AppDatabase.Companion.DB_VERSION


@Database(entities = [ShoppingItem::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "shopper_db"
    }
}