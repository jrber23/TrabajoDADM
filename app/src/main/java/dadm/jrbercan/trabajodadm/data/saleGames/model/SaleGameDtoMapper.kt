package dadm.jrbercan.trabajodadm.data.saleGames.model

import dadm.jrbercan.trabajodadm.domain.model.Game
import retrofit2.Response
import java.io.IOException

fun SaleGameDto.toDomain(): Game = Game(id = dealId, title = title, price = salePrice)

fun Response<SaleGameDto>.toDomain() =
    if (isSuccessful) Result.success((body() as SaleGameDto).toDomain())
    else Result.failure(IOException())