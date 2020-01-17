package noblur.com.fdj.data.source.repository

import noblur.com.fdj.data.Team


class TeamRepository(
    private val teamRemoteDataSource: TeamDataSource,
    private val teamLocalDataSource: TeamDataSource
): TeamDataSource {

    override fun getTeams(callback: TeamDataSource.LoadTeamsCallback) {

    }

    override fun saveTeams(teams: List<Team>) {

    }

    override fun getTeams(leagueId: String, callback: TeamDataSource.LoadTeamsCallback) {
        teamRemoteDataSource.getTeams(leagueId,object :TeamDataSource.LoadTeamsCallback{
            override fun onTeamsLoaded(teams: List<Team>) {

                callback.onTeamsLoaded(teams)
            }

            override fun onDataNotAvailable(code: Int) {

                callback.onDataNotAvailable(code)

            }

        })
    }



    companion object {

        private var INSTANCE: TeamRepository? = null


        @JvmStatic fun getInstance(teamRemoteDataSource: TeamDataSource,
                                   teamLocalDataSource: TeamDataSource
        ) =
            INSTANCE
                ?: synchronized(TeamRepository::class.java) {
                INSTANCE
                    ?: TeamRepository(
                        teamRemoteDataSource,
                        teamLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }




}