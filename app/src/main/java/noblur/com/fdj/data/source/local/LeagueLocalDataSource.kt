package noblur.com.fdj.data.source.local


import android.util.Log
import androidx.annotation.VisibleForTesting
import noblur.com.fdj.data.League
import noblur.com.fdj.data.source.repository.LeagueDataSource
import noblur.com.fdj.utils.AppExecutors


class LeagueLocalDataSource(
    val appExecutors: AppExecutors,
    val leagueDao: LeagueDao
): LeagueDataSource {


    override fun getLeagues(callback: LeagueDataSource.LoadLeaguesCallback) {

        appExecutors.diskIO.execute {

            val leagues = leagueDao.getLeagues()
            appExecutors.diskIO.execute {
                if (leagues.isEmpty()){

                    callback.onDataNotAvailable(500)
                }else{
                    callback.onLeaguesLoaded(leagues)

                }

            }
        }


    }


    override fun saveLeagues(league: List<League>) {

        appExecutors.diskIO.execute { leagueDao.insertLeagues(league) }


    }

    override fun getLeague(query: String, callback: LeagueDataSource.LoadLeagueCallback) {
        appExecutors.diskIO.execute {

            val league = leagueDao.getLeagueByName(query)
            appExecutors.diskIO.execute {
                if (league==null){
                    callback.onDataNotAvailable(500)
                }else{
                    callback.onLeagueLoaded(league)
                }

            }
        }
    }

    companion object {
        private var INSTANCE: LeagueLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, deviceDao: LeagueDao): LeagueLocalDataSource {
            if (INSTANCE == null) {
                synchronized(LeagueLocalDataSource::javaClass) {
                    INSTANCE =
                        LeagueLocalDataSource(appExecutors, deviceDao)
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