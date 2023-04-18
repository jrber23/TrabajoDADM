package dadm.jrbercan.trabajodadm.data.favouritesGames

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.TABLE_NAME
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteGamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteGame(game: FavouriteGameDto)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllFavouriteGames() : Flow<List<FavouriteGameDto>>

}