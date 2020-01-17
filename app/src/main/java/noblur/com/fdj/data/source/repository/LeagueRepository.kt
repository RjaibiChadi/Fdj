package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.League
import noblur.com.fdj.data.Leagues


class LeagueRepository(
    private val leagueRemoteDataSource: LeagueDataSource,
    private val leagueLocalDataSource: LeagueDataSource
): LeagueDataSource {


    override fun getLeagues(callback: LeagueDataSource.LoadLeaguesCallback) {

        leagueLocalDataSource.getLeagues(object :LeagueDataSource.LoadLeaguesCallback{
            override fun onLeaguesLoaded(league: List<League>) {

                callback.onLeaguesLoaded(league)

            }

            override fun onDataNotAvailable(code: Int) {

                getLeagueRemote(callback)
            }

        })



    }

    private fun getLeagueRemote(callback: LeagueDataSource.LoadLeaguesCallback) {

        leagueRemoteDataSource.getLeagues(object :LeagueDataSource.LoadLeaguesCallback{
            override fun onLeaguesLoaded(league: List<League>) {
                callback.onLeaguesLoaded(league)
                leagueLocalDataSource.saveLeagues(league)
            }

            override fun onDataNotAvailable(code: Int) {
                callback.onDataNotAvailable(code)
            }


        })
    }

    override fun saveLeagues(league: List<League>) {
    }

    override fun getLeague(query: String, callback: LeagueDataSource.LoadLeagueCallback) {

        leagueLocalDataSource.getLeague(query,object :LeagueDataSource.LoadLeagueCallback{
            override fun onLeagueLoaded(league: League) {

                callback.onLeagueLoaded(league)
            }

            override fun onDataNotAvailable(code: Int) {

                callback.onDataNotAvailable(code)
            }


        })
    }


    companion object {

        private var INSTANCE: LeagueRepository? = null


        @JvmStatic fun getInstance(leagueRemoteDataSource: LeagueDataSource,
                                   leagueLocalDataSource: LeagueDataSource
        ) =
            INSTANCE
                ?: synchronized(LeagueRepository::class.java) {
                INSTANCE
                    ?: LeagueRepository(
                        leagueRemoteDataSource,
                        leagueLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }




}