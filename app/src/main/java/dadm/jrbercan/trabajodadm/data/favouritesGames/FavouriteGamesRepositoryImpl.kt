package dadm.jrbercan.trabajodadm.data.favouritesGames

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteGamesRepositoryImpl @Inject constructor(private val favouriteGamesDataSource: FavouriteGamesDataSource) :
    FavouriteGamesRepository {
    /* override suspend fun addFavouriteGame(game: FavouriteGameDto) {
        favouriteGamesDataSource.addFavouriteGame(game)
    } */

    override fun getAllFavouriteGames(): Flow<List<FavouriteGameDto>> =
        favouriteGamesDataSource.getAllFavouriteGames()
}