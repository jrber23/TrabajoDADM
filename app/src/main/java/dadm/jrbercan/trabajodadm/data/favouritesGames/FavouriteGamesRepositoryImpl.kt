package dadm.jrbercan.trabajodadm.data.favouritesGames

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.toDomain
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.toDto
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteGamesRepositoryImpl @Inject constructor(private val favouriteGamesDataSource: FavouriteGamesDataSource) :
    FavouriteGamesRepository {
    override suspend fun addFavouriteGame(game: Game) {
        favouriteGamesDataSource.addFavouriteGame(game.toDto())
    }

    override fun getAllFavouriteGames(): Flow<List<Game>> =
        favouriteGamesDataSource.getAllFavouriteGames().map { list ->
            list.map { favouriteGameDto ->
                favouriteGameDto.toDomain()
            }
        }
}