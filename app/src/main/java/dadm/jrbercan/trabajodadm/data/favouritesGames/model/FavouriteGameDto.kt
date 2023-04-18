package dadm.jrbercan.trabajodadm.data.favouritesGames.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_ID
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_PRICE
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.COLUMN_TITLE
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.TableBD.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavouriteGameDto(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = COLUMN_ID) val dealId: String,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_PRICE) val salePrice: String
)