package noblur.com.fdj.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.Team
import noblur.com.fdj.data.source.repository.LeagueDataSource
import noblur.com.fdj.data.source.repository.PlayerDataSource
import noblur.com.fdj.data.source.repository.TeamDataSource


class PlayerRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: FdjService
) : PlayerDataSource {

    override fun getPlayers(callback: PlayerDataSource.LoadPlayersCallback) {

    }

    override fun savePlayers(players: List<Player>) {

    }

    override fun getPlayer(nameTeam: String, callback: PlayerDataSource.LoadPlayersCallback) {


        compositeDisposable?.add(api.getPlayersByTeam(nameTeam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->
                    callback.onPlayersLoaded(it.player)
                },
                { error ->

                    callback.onDataNotAvailable(500)

                }
            )
        )
    }

    companion object {
        private var INSTANCE: PlayerRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: FdjService
        ): PlayerRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(PlayerRemoteDataSource::javaClass) {
                    INSTANCE = PlayerRemoteDataSource(compositeDisposable,api)
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