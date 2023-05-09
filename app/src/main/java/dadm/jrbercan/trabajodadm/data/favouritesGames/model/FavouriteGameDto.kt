package dadm.jrbercan.trabajodadm.data.favouritesGames.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_FAVOURITE
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_ID
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_PRICE
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_THUMB
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_TITLE
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavouriteGameDto(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = COLUMN_ID) val dealId: String,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_PRICE) val salePrice: String,
    @ColumnInfo(name = COLUMN_THUMB) val thumb: String,
    //@ColumnInfo(name = COLUMN_FAVOURITE) val favourite: Boolean,
    /*@ColumnInfo(name = COLUMN_DEALID) val dealId: String,
    @ColumnInfo(name = COLUMN_STEAMID) val steamAppId: String,
    @ColumnInfo(name = COLUMN_THUMBID) val thumb: String*/
)