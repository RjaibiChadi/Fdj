package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.Team


interface TeamDataSource {


    interface LoadTeamsCallback {

        fun onTeamsLoaded(teams: List<Team>)

        fun onDataNotAvailable(code:Int)
    }

    interface LoadTeamCallback {

        fun onTeamLoaded(team: Team)

        fun onDataNotAvailable(code:Int)
    }


    fun getTeams(callback: LoadTeamsCallback)

    fun saveTeams(teams: List<Team>)

    fun getTeams(leagueId: String, callback: LoadTeamsCallback)

}