package noblur.com.fdj.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Team
import noblur.com.fdj.data.source.repository.LeagueDataSource
import noblur.com.fdj.data.source.repository.TeamDataSource


class TeamRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: FdjService
) : TeamDataSource {


    override fun getTeams(callback: TeamDataSource.LoadTeamsCallback) {

    }

    override fun saveTeams(teams: List<Team>) {

    }

    override fun getTeams(leagueId: String, callback: TeamDataSource.LoadTeamsCallback) {

        compositeDisposable?.add(api.getTeamsByLeague(leagueId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->
                    callback.onTeamsLoaded(it.teams)
                },
                { error ->

                    callback.onDataNotAvailable(500)

                }
            )
        )

    }

    companion object {
        private var INSTANCE: TeamRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: FdjService
        ): TeamRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(TeamRemoteDataSource::javaClass) {
                    INSTANCE = TeamRemoteDataSource(compositeDisposable,api)
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