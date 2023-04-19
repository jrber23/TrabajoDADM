package dadm.jrbercan.trabajodadm.data.moreGameInfo.model

import retrofit2.Response
import java.io.IOException

fun Response<GameDealsDto>.toDomain() =
    if (isSuccessful) Result.success((body() as GameDealsDto))
    else Result.failure(IOException())