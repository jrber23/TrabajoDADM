package dadm.jrbercan.trabajodadm.data.saleGames.model

import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.domain.model.Game

fun SaleGameDto.toDomain() : Game = Game(id = dealID, title = title, price = salePrice)

fun Game.toDto() : SaleGameDto = SaleGameDto(dealID = id, title = title, salePrice = price)