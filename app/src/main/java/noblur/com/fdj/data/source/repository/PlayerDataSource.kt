package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.Player


interface PlayerDataSource {


    interface LoadPlayersCallback {

        fun onPlayersLoaded(players: List<Player>)

        fun onDataNotAvailable(code:Int)
    }

    interface LoadPlayerCallback {

        fun onPlayerLoaded(player: Player)

        fun onDataNotAvailable(code:Int)
    }


    fun getPlayers(callback: LoadPlayersCallback)

    fun savePlayers(players: List<Player>)


    fun getPlayer(nameTeam: String, callback: LoadPlayersCallback)

}