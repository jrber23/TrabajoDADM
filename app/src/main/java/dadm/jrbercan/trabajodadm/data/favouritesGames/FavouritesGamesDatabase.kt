package dadm.jrbercan.trabajodadm.data.favouritesGames

import androidx.room.Database
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto

@Database(entities = [FavouriteGameDto::class], version = 1)
abstract class FavouritesGamesDatabase {

    abstract fun favouritesGamesDao() : FavouriteGamesDao

}