package dadm.jrbercan.trabajodadm.data.favouritesGames.model

import dadm.jrbercan.trabajodadm.domain.model.Game

fun FavouriteGameDto.toDomain() : Game = Game(id = dealId, title = title, price = salePrice, favourite = true, dealId = "", steamAppId = "", thumb = thumb)

 fun Game.toDto() : FavouriteGameDto = FavouriteGameDto(dealId = id, title = title, salePrice = price, thumb = thumb)