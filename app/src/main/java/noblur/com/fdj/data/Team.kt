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
data class Team(
                @PrimaryKey(autoGenerate = true)
                var id:Int,
                var idTeam:String,
                  var strTeam:String,
                  var strTeamBadge:String)

data class Teams(
    var teams:List<Team>
)