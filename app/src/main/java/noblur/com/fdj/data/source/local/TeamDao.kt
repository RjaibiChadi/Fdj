package noblur.com.fdj.data.source.local

import androidx.room.*
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Team

@Dao
interface TeamDao {


    @Query("SELECT * FROM team")
    fun getTeams():List<Team>

    @Query("SELECT * FROM team WHERE id = :teamId")
    fun getTeamById(teamId : Int): Team?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Team>)

    @Query("DELETE FROM team")
    fun  deleteAllTeams()


}