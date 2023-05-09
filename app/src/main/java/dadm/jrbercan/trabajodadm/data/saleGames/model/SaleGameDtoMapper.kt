package dadm.jrbercan.trabajodadm.data.saleGames.model

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game

fun SaleGameDto.toDomain() : Game = Game(id = gameID, title = title, price = salePrice, favourite = false, thumb = thumb, steamAppId = steamAppID, dealId = dealID)

 //fun Game.toDto() : SaleGameDto = SaleGameDto(dealID = id, title = title, salePrice = price, thumb = thumb)