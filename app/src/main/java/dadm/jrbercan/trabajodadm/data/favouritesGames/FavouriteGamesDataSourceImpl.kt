package dadm.jrbercan.trabajodadm.data.favouritesGames

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteGamesDataSourceImpl @Inject constructor(private val favouriteGamesDao: FavouriteGamesDao) :
    FavouriteGamesDataSource {
    override suspend fun addFavouriteGame(game: FavouriteGameDto) {
        favouriteGamesDao.addFavouriteGame(game)
    }

    override fun getAllFavouriteGames(): Flow<List<FavouriteGameDto>> =
        favouriteGamesDao.getAllFavouriteGames()
}