package dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model

import retrofit2.Response
import java.io.IOException

fun Response<SteamGameDto>.toDomain() =
    if (isSuccessful) Result.success((body() as SteamGameDto))
    else Result.failure(IOException())