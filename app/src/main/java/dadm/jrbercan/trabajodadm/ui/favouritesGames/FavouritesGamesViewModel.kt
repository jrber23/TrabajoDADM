package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import androidx.lifecycle.*
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class FavouritesGamesViewModel @Inject constructor(
    private val favouriteGamesRepository: FavouriteGamesRepository
) : ViewModel() {
    val favouriteGames : LiveData<List<Game?>> = favouriteGamesRepository.getAllFavouriteGames().asLiveData()

    fun deleteAllFavouriteGames(){
        viewModelScope.launch {
            favouriteGamesRepository.deleteAllFavouriteGames()
        }
    }

    fun deleteGameFromFavourites(game : Game){
        viewModelScope.launch{ favouriteGamesRepository.deleteFavouriteGame(game) }
    }

}