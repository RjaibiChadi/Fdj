package noblur.com.fdj.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.Team

@Database(entities = arrayOf(
    League::class,
    Player::class,
    Team::class
   ),version = 1,exportSchema = false)
abstract class FdjDatabase: RoomDatabase() {


        abstract fun leagueDao(): LeagueDao
        abstract fun teamDao():TeamDao
        abstract fun playerDao():PlayerDao

        companion object {

            private var INSTANCE : FdjDatabase?=null

            private val lock = Any()

            fun getInstance(context: Context): FdjDatabase {
                synchronized(lock){
                    if (INSTANCE ==null){

                            INSTANCE = Room.databaseBuilder(context.applicationContext,
                                FdjDatabase::class.java,"fdj.db")
                                .fallbackToDestructiveMigration()
                                .build()
                    }

                    return INSTANCE!!


                }
            }
        }

}