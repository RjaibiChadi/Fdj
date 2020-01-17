package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.League
import noblur.com.fdj.data.Leagues


interface LeagueDataSource {


    interface LoadLeaguesCallback {

        fun onLeaguesLoaded(league: List<League>)

        fun onDataNotAvailable(code:Int)
    }



    interface LoadLeagueCallback {

        fun onLeagueLoaded(league: League)

        fun onDataNotAvailable(code:Int)
    }


    fun getLeagues(callback: LoadLeaguesCallback)


    fun saveLeagues(league: List<League>)


    fun getLeague(query: String, callback: LoadLeagueCallback)

}