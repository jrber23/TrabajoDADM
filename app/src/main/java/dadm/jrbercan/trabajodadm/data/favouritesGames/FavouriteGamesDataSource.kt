package dadm.jrbercan.trabajodadm.data.favouritesGames

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import kotlinx.coroutines.flow.Flow

interface FavouriteGamesDataSource {

    suspend fun addFavouriteGame(game: FavouriteGameDto)

    fun getAllFavouriteGames() : Flow<List<FavouriteGameDto>>

}