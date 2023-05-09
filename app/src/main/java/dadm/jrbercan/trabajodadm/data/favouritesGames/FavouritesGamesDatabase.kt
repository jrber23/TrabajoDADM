package dadm.jrbercan.trabajodadm.data.favouritesGames

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto

@Database(entities = [FavouriteGameDto::class], version = 1)
abstract class FavouritesGamesDatabase : RoomDatabase() {

    abstract fun favouritesGamesDao() : FavouriteGamesDao

}