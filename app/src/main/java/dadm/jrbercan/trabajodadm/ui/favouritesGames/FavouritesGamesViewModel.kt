package dadm.jrbercan.trabajodadm.ui.favouritesGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlin.random.Random

class FavouritesGamesViewModel : ViewModel() {
    private val _game : MutableLiveData<List<Game>> = getFavouriteGames()
    val game : LiveData<List<Game>>
        get() =_game

    private fun getFavouriteGames(): MutableLiveData<List<Game>> {
        val list: ArrayList<Game> = ArrayList(10)
        for (i in 1..10) {
            val numPrice = getRandom(0, 10)
            val numTitle = (0..10).random().toString()
            val newGame = Game("Title  #$numTitle", "14.99 $")
            list.add(newGame)
        }
        val mutableLiveData : MutableLiveData<List<Game>> = MutableLiveData(list.toList())
        return mutableLiveData
    }

    private fun getRandom(min: Int, max: Int): Double {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + Random.nextDouble() * (max - min)
    }


}