package noblur.com.fdj.data.source.local

import androidx.room.*
import noblur.com.fdj.data.League

@Dao
interface LeagueDao {


    @Query("SELECT * FROM league")
    fun getLeagues():List<League>

    @Query("SELECT * FROM league  WHERE strLeague LIKE '%' || :query || '%'")
    fun getLeagueByName(query: String): League?

    @Query("SELECT * FROM league WHERE id = :deviceId")
    fun getLeagueById(deviceId : Int): League?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(league: League)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeagues(leaques: List<League>)

    @Query("DELETE FROM league")
    fun  deleteAllLeagues()


}