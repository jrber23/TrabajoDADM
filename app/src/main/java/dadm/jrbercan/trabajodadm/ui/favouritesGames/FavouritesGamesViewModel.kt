package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import androidx.lifecycle.*
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class FavouritesGamesViewModel @Inject constructor(
    private val favouriteGamesRepository: FavouriteGamesRepository
) : ViewModel() {
    val game : LiveData<List<FavouriteGameDto>> = favouriteGamesRepository.getAllFavouriteGames().asLiveData()

    private fun getFavouriteGames(): MutableLiveData<List<FavouriteGameDto>> {
        val list: ArrayList<FavouriteGameDto> = ArrayList(10)
        for (i in 1..10) {
            val numPrice = getRandom(0, 10)
            val numTitle = (0..10).random().toString()
            val newGame = FavouriteGameDto("","Title  #$numTitle", "14.99 $", "")
            list.add(newGame)
        }
        val mutableLiveData : MutableLiveData<List<FavouriteGameDto>> = MutableLiveData(list.toList())
        return mutableLiveData
    }

    private fun getRandom(min: Int, max: Int): Double {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + Random.nextDouble() * (max - min)
    }

    fun deleteAllFavouriteGames(){
        viewModelScope.launch {
            favouriteGamesRepository.deleteAllFavouriteGames()
        }
    }

    /* fun deleteGameAtPosition(position: Int){
        _game.value?.toMutableList()?.apply {
            removeAt(position)
            _game.value = this
        }
    } */
}