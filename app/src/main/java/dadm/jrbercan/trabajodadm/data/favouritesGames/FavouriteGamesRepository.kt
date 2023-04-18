package dadm.jrbercan.trabajodadm.data.favouritesGames

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface FavouriteGamesRepository {

    // suspend fun addFavouriteGame(game: FavouriteGameDto)

    fun getAllFavouriteGames(): Flow<List<FavouriteGameDto>>

}