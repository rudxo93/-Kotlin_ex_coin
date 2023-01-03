package com.duran.gyoung_tae_93.pj.coinmonitoring.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duran.gyoung_tae_93.pj.coinmonitoring.db.dao.InterestCoinDao
import com.duran.gyoung_tae_93.pj.coinmonitoring.db.entity.InterestCoinEntity

@Database(entities = [InterestCoinEntity::class], version = 1)
abstract class CoinPriceDatabase : RoomDatabase() {

    abstract fun interestCoinDao() : InterestCoinDao

    companion object {
        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(
            context : Context
        ) : CoinPriceDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}