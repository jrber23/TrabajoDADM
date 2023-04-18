package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class FavouritesGamesViewModel @Inject constructor(
    private val favouriteGamesRepository: FavouriteGamesRepository
) : ViewModel() {
    private val _game : MutableLiveData<List<Game>> = MutableLiveData()
    val game : LiveData<List<Game>>
        get() =_game

    private fun getFavouriteGames(): MutableLiveData<List<Game>> {
        val list: ArrayList<Game> = ArrayList(10)
        for (i in 1..10) {
            val numPrice = getRandom(0, 10)
            val numTitle = (0..10).random().toString()
            val newGame = Game("","Title  #$numTitle", "14.99 $")
            list.add(newGame)
        }
        val mutableLiveData : MutableLiveData<List<Game>> = MutableLiveData(list.toList())
        return mutableLiveData
    }

    private fun getRandom(min: Int, max: Int): Double {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + Random.nextDouble() * (max - min)
    }

    fun deleteAllGames(){
        //Log.d("deleteAllGames", "deleteAllGames pulsado")
        /*viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favouritesRepository.deleteAllQuotationsFromDB()
            }
        }
       */
        _game.value = emptyList()
    }

    fun deleteGameAtPosition(position: Int){
        _game.value?.toMutableList()?.apply {
            removeAt(position)
            _game.value = this
        }
    }




}