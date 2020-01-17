package noblur.com.fdj.data.source.local


import androidx.annotation.VisibleForTesting
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.source.repository.PlayerDataSource
import noblur.com.fdj.utils.AppExecutors


class PlayerLocalDataSource(
    val appExecutors: AppExecutors,
    val playerDao: PlayerDao
): PlayerDataSource {


    override fun getPlayers(callback: PlayerDataSource.LoadPlayersCallback) {

    }

    override fun savePlayers(players: List<Player>) {

    }

    override fun getPlayer(nameTeam: String, callback: PlayerDataSource.LoadPlayersCallback) {

    }


    companion object {
        private var INSTANCE: PlayerLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, playerDao: PlayerDao): PlayerLocalDataSource {
            if (INSTANCE == null) {
                synchronized(PlayerLocalDataSource::javaClass) {
                    INSTANCE =
                        PlayerLocalDataSource(appExecutors, playerDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }



}