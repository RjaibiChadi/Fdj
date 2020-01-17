package noblur.com.fdj.data.source.local


import androidx.annotation.VisibleForTesting
import noblur.com.fdj.data.Team
import noblur.com.fdj.data.source.repository.TeamDataSource
import noblur.com.fdj.utils.AppExecutors


class TeamLocalDataSource(
    val appExecutors: AppExecutors,
    val teamDao: TeamDao
): TeamDataSource {

    override fun getTeams(callback: TeamDataSource.LoadTeamsCallback) {

    }

    override fun saveTeams(teams: List<Team>) {

    }

    override fun getTeams(leagueId: String, callback: TeamDataSource.LoadTeamsCallback) {

    }


    companion object {
        private var INSTANCE: TeamLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, teamDao: TeamDao): TeamLocalDataSource {
            if (INSTANCE == null) {
                synchronized(TeamLocalDataSource::javaClass) {
                    INSTANCE =
                        TeamLocalDataSource(appExecutors, teamDao)
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