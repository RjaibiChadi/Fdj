package noblur.com.fdj.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model class for a team.
 *
 * @param id            id of the team in database
 * @param idTeam        id of the team
 * @param strTeam        name of the team
 * @param strTeamBadge    logo of the team
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