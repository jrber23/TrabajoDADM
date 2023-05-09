package dadm.jrbercan.trabajodadm.ui.moreGameInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoRepository
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.GameDealsDto
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.ShopsDto
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.SteamGameDto
import dadm.jrbercan.trabajodadm.domain.model.GameDeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreGameInfoViewModel @Inject constructor(private val moreGameInfoRepository: MoreGameInfoRepository) : ViewModel() {
    private val _gameDeals : MutableLiveData<GameDealsDto> = MutableLiveData()
    val gameDeals : LiveData<GameDealsDto>
        get() =_gameDeals

    private val _shops: MutableLiveData<List<ShopsDto>> = MutableLiveData()
    val shops: LiveData<List<ShopsDto>>
        get() =_shops

    private val _steamGame: MutableLiveData<SteamGameDto> = MutableLiveData()
    val steamGame: LiveData<SteamGameDto>
        get() =_steamGame

    private val _gameDealsWithStoreName: MutableLiveData<List<GameDeals>> = MutableLiveData()
    val gameDealsWithStoreName: LiveData<List<GameDeals>>
        get() = _gameDealsWithStoreName

    fun getGameDeals(number: String) {
        viewModelScope.launch(CoroutineName("GetGameDealsInfoFunction")) {
            try {
                moreGameInfoRepository.getGameDeals(number).fold(onSuccess = {_gameDeals.value = it}, onFailure = { })
                moreGameInfoRepository.getAllShops().fold(onSuccess = { _shops.value = it }, onFailure = { })
                val lista: MutableList<GameDeals> = arrayListOf()
                for (gameDeal in gameDeals.value!!.deals) {
                    for (shop in shops.value!!) {
                        if (gameDeal.storeID == shop.storeID) {
                            lista.add(GameDeals(shop.images.logo,shop.storeName,gameDeal.price+" $"))
                        }
                    }
                }
                _gameDealsWithStoreName.value = lista
            } catch (e: Exception) {
                Log.d("FAILURE IN getGameDeals()", e.toString())
            }
        }
    }

    fun getGameInfo(number: String, language: String) {
        viewModelScope.launch(CoroutineName("GetMoreGameInfoFunction")) {
            try {
                moreGameInfoRepository.getSteamInfo(number,language).fold(onSuccess = {_steamGame.value = it}, onFailure = { })
            } catch (e: Exception) {
                Log.d("FAILURE IN getGameInfo()", e.toString())
            }
        }
    }
}