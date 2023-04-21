package dadm.jrbercan.trabajodadm.ui.lastSales

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.saleGames.SaleGamesApiService
import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import dadm.jrbercan.trabajodadm.data.saleGames.model.toDomain
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LastSalesViewModel @Inject constructor() : ViewModel() {
    private val _game : MutableLiveData<List<SaleGameDto>> = MutableLiveData()
    val game : LiveData<List<SaleGameDto>>
        get() =_game

    init {
        getAllSaleGames()
    }

    private fun getFavouriteGames(): MutableLiveData<List<Game>> {
        val list: ArrayList<Game> = ArrayList(10)
        for (i in 1..10) {
            val numPrice = getRandom(0, 10)
            val numTitle = (0..10).random().toString()
            val newGame = Game("", "Title  #$numTitle", "14.99 $")
            list.add(newGame)
        }
        val mutableLiveData : MutableLiveData<List<Game>> = MutableLiveData(list.toList())
        return mutableLiveData
    }

    fun getAllSaleGames() {
        viewModelScope.launch(CoroutineName("GetAllSaleGamesFunction")) {
            try {
                val listResult = SaleGamesApiService.SaleGamesApi.retrofitService.getAllSaleGames()
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
                val gameOfListtoGame = mutableListOf<SaleGameDto>()
                for (game in listResult) {
                    gameOfListtoGame.add(SaleGameDto(game.cheapestDealID,game.external,game.cheapest,game.thumb,game.gameID,game.steamAppID))
                }
                _game.value = gameOfListtoGame
            } catch (e: Exception) {
                Log.d("FAILURE", e.toString())
            }
        }
    }

    fun addToFavourites() {
        viewModelScope.launch(CoroutineName("AddToFavouritesGamesMethod")) {

        }
    }

    private fun getRandom(min: Int, max: Int): Double {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + Random.nextDouble() * (max - min)
    }

}