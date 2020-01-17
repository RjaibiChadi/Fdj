package noblur.com.fdj.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model class for a League.
 *
 * @param id_league       title of the task
 * @param str_league description of the task
 * @param id          id of the task
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