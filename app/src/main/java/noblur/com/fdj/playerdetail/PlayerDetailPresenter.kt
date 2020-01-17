package noblur.com.fdj.playerdetail

import android.util.Log
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.source.repository.PlayerDataSource
import noblur.com.fdj.data.source.repository.PlayerRepository

class PlayerDetailPresenter(
   val teamStr: String,
   val playerRepository: PlayerRepository,
   val playerDetailView: PlayerDetailContract.View
) :PlayerDetailContract.Presenter {


    init {
        playerDetailView.presenter = this
    }


    override fun start() {

        playerRepository.getPlayer(teamStr,object :PlayerDataSource.LoadPlayersCallback{
            override fun onPlayersLoaded(players: List<Player>) {

                playerDetailView.showPlayers(players)
            }

            override fun onDataNotAvailable(code: Int) {


            }


        })

    }


}