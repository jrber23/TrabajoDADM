package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import androidx.lifecycle.*
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.saleGames.SaleGamesApiService
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepository
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class FavouritesGamesViewModel @Inject constructor(
    private val favouriteGamesRepository: FavouriteGamesRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val favouriteGames : LiveData<List<Game>> = favouriteGamesRepository.getAllFavouriteGames().asLiveData()

    private lateinit var email : String

    init {

        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getEmail().collect{ n ->
                email = n
            }
        }
    }

    fun deleteAllFavouriteGames(games : List<Game>) {
        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getEmail().collect{ n ->
                email = n
            }
        }
        Log.d("FavouriteGames", favouriteGames.value.toString())
        for (game in games) {
            viewModelScope.launch {
                SaleGamesApiService.SaleGamesApi.retrofitService.deletePriceAlert(
                    email,
                    game!!.id
                )
            }
        }
        viewModelScope.launch {
            favouriteGamesRepository.deleteAllFavouriteGames()
        }
    }

    fun deleteGameFromFavourites(game : Game){
        viewModelScope.launch{ favouriteGamesRepository.deleteFavouriteGame(game) }
        viewModelScope.launch { SaleGamesApiService.SaleGamesApi.retrofitService.deletePriceAlert(email, game.id) }
    }

}