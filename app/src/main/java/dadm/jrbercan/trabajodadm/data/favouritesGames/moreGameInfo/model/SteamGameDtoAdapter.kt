package dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model

import com.squareup.moshi.*

class SteamGameDtoAdapter: JsonAdapter<SteamGameDto>() {
    @FromJson
    override fun fromJson(reader: JsonReader): SteamGameDto? {
        var data: IdSteam? = null

        reader.beginObject()
        while (reader.hasNext()) {
            val key = reader.nextName()
            val jsonAdapter = Moshi.Builder().build().adapter(IdSteam::class.java)
            data = jsonAdapter.fromJson(reader)
        }
        reader.endObject()

        return SteamGameDto(data!!)
    }

    override fun toJson(writer: JsonWriter, value: SteamGameDto?) {
        //NO NECESARIO
    }
}