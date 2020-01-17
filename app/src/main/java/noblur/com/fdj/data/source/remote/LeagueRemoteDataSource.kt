package noblur.com.fdj.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.fdj.data.League
import noblur.com.fdj.data.source.repository.LeagueDataSource



class LeagueRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: FdjService
) : LeagueDataSource {

    override fun getLeagues(callback: LeagueDataSource.LoadLeaguesCallback) {


        compositeDisposable?.add(api.getLeagues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->
                    callback.onLeaguesLoaded(it.leagues)
                },
                { error ->

                    callback.onDataNotAvailable(500)
                }
            )
        )

    }

    override fun saveLeagues(league: List<League>) {

    }

    override fun getLeague(query: String, callback: LeagueDataSource.LoadLeagueCallback) {

    }


    companion object {
        private var INSTANCE: LeagueRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: FdjService
        ): LeagueRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(LeagueRemoteDataSource::javaClass) {
                    INSTANCE = LeagueRemoteDataSource(compositeDisposable,api)
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