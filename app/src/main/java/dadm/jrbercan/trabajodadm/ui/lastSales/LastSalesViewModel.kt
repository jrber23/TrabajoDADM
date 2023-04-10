package dadm.jrbercan.trabajodadm.ui.lastSales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dadm.jrbercan.trabajodadm.domain.model.Game
import dadm.jrbercan.trabajodadm.ui.favouritesGames.FavouritesGamesViewModel
import kotlin.random.Random

class LastSalesViewModel : ViewModel() {
    private val _game : MutableLiveData<ArrayList<Game>> = getFavouriteGames()
    val game : LiveData<ArrayList<Game>>
        get() =_game

    private fun getFavouriteGames(): MutableLiveData<ArrayList<Game>> {
        val list: ArrayList<Game> = ArrayList(10)
        for (i in 1..10) {
            val numPrice = getRandom(0, 10)
            val numTitle = (0..10).random().toString()
            val newGame = Game("Title  #$numTitle", "14.99 $")
            list.add(newGame)
        }
        return MutableLiveData(list)
    }

    private fun getRandom(min: Int, max: Int): Double {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + Random.nextDouble() * (max - min)
    }

}