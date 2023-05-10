package dadm.jrbercan.trabajodadm.ui.lastSales

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.saleGames.SaleGamesApiService
import dadm.jrbercan.trabajodadm.data.saleGames.model.toDomain
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepository
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates
import kotlin.random.Random

@HiltViewModel
class LastSalesViewModel @Inject constructor(private val favouriteGamesRepository: FavouriteGamesRepository, private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _game : MutableLiveData<List<Game>> = MutableLiveData()
    val game : LiveData<List<Game>>
        get() =_game
    private lateinit var defaultPrice : String
    private lateinit var email : String

    init {
        getAllSaleGames()
        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getPrice().collect { price ->
                defaultPrice = price
            }
        }
        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getEmail().collect{ n ->
                email = n
            }
        }
    }

    private fun getAllSaleGames() {
        viewModelScope.launch(CoroutineName("GetAllSaleGamesFunction")) {
            try {
                val listResult = SaleGamesApiService.SaleGamesApi.retrofitService.getAllSaleGames().map{it -> it.toDomain()}
                _game.value = listResult
            } catch (e: Exception) {
                Log.d("FAILURE", e.toString())
            }
        }
    }

    fun getGamesByTitle(title: String?) {
        viewModelScope.launch(CoroutineName("GetGamesByTitleFunction")) {
            try {
                val listResult = SaleGamesApiService.SaleGamesApi.retrofitService.getGamesByTitle(title)
                val gameOfListToGame = mutableListOf<Game>()
                for (game in listResult) {
                    gameOfListToGame.add(Game(game.gameID,game.external,game.cheapest,game.thumb,false, game.steamAppID, game.cheapestDealID))
                }
                _game.value = gameOfListToGame
            } catch (e: Exception) {
                Log.d("FAILURE", e.toString())
            }
        }
    }

    fun addToFavourites(game : Game) {
        viewModelScope.launch(CoroutineName("AddToFavouritesGamesMethod")) {
            favouriteGamesRepository.addFavouriteGame(game)
        }
    }

    fun getDefaultPriceSettings() : String {

        return defaultPrice
    }

    fun getEmailSettings() : String {

        return email
    }

    fun setPriceAlert(email : String, gameId : String, price : String) : String{
        var res = true
        viewModelScope.launch{
            res = SaleGamesApiService.SaleGamesApi.retrofitService.setPriceAlert(email, gameId, price)
        }
        return "$res $email"


    }
}