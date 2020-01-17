package noblur.com.fdj.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model class for a League.
 *
 * @param id                id of the league in database
 * @param id_league         id of the league
 * @param str_league        name of the league
 * @param strSport          type of league
 * @param strLeagueAlternate
 */
@Entity
data class League(
                    @PrimaryKey(autoGenerate = true)
                    var id:Int,
                    var idLeague:String,
                    var strLeague:String,
                    var strSport:String,
                    var strLeagueAlternate:String?
                   )

data class Leagues(
    var leagues:List<League>
)