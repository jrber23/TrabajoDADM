package dadm.jrbercan.trabajodadm.data.moreGameInfo.model

import retrofit2.Response
import java.io.IOException

fun Response<List<ShopsDto>>.toDomain() =
    if (isSuccessful) Result.success((body() as List<ShopsDto>))
    else Result.failure(IOException())