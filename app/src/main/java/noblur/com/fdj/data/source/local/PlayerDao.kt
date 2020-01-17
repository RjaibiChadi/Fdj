package noblur.com.fdj.data.source.local

import androidx.room.*
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.Team

@Dao
interface PlayerDao {


    @Query("SELECT * FROM player")
    fun getPlayers():List<Player>

    @Query("SELECT * FROM player WHERE id = :playerId")
    fun getPlayerById(playerId : Int): Player?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayers(players: List<Player>)

    @Query("DELETE FROM player")
    fun  deleteAllPlayers()


}