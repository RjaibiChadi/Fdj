package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.Player


class PlayerRepository(
    private val playerRemoteDataSource: PlayerDataSource,
    private val playerLocalDataSource: PlayerDataSource
): PlayerDataSource {



    override fun getPlayers(callback: PlayerDataSource.LoadPlayersCallback) {

    }

    override fun savePlayers(players: List<Player>) {

    }

    override fun getPlayer(nameTeam: String, callback: PlayerDataSource.LoadPlayersCallback) {

        playerRemoteDataSource.getPlayer(nameTeam,object :PlayerDataSource.LoadPlayersCallback{
            override fun onPlayersLoaded(players: List<Player>) {

                callback.onPlayersLoaded(players)
            }

            override fun onDataNotAvailable(code: Int) {

                callback.onDataNotAvailable(code)
            }


        })
    }



    companion object {

        private var INSTANCE: PlayerRepository? = null


        @JvmStatic fun getInstance(playerRemoteDataSource: PlayerDataSource,
                                   playerLocalDataSource: PlayerDataSource
        ) =
            INSTANCE
                ?: synchronized(PlayerRepository::class.java) {
                INSTANCE
                    ?: PlayerRepository(
                        playerRemoteDataSource,
                        playerLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }


}