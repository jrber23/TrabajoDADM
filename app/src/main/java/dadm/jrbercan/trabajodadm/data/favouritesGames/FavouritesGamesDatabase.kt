package dadm.jrbercan.trabajodadm.data.favouritesGames

import androidx.room.Database
import androidx.room.RoomDatabase
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto

@Database(entities = [FavouriteGameDto::class], version = 1)
abstract class FavouritesGamesDatabase : RoomDatabase() {

    abstract fun favouritesGamesDao() : FavouriteGamesDao

}