package noblur.com.fdj.homepage

import android.util.Log
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Leagues
import noblur.com.fdj.data.Team
import noblur.com.fdj.data.source.repository.LeagueDataSource
import noblur.com.fdj.data.source.repository.LeagueRepository
import noblur.com.fdj.data.source.repository.TeamDataSource
import noblur.com.fdj.data.source.repository.TeamRepository
import java.util.*

class HomePagePresenter(
    val leagueRepository: LeagueRepository,
    val teamRepository: TeamRepository,
    val homePageView: HomePageContract.View
):HomePageContract.Presenter {

    init {
            homePageView.presenter = this
    }



    override fun start() {

            loadLeagues()
    }

    override fun loadLeagues() {

        leagueRepository.getLeagues(object :LeagueDataSource.LoadLeaguesCallback{
            override fun onLeaguesLoaded(leagues : List<League>) {
            }
            override fun onDataNotAvailable(code: Int) {

            }
        })

    }

    override fun loadTeamsOfLeague(query:String) {
        homePageView.setLoadingIndicator(true)


        leagueRepository.getLeague(query,object :LeagueDataSource.LoadLeagueCallback{
            override fun onLeagueLoaded(league: League) {
                homePageView.setLoadingIndicator(false)

                getTeamsByLeagues(league.idLeague)
            }

            override fun onDataNotAvailable(code: Int) {
                homePageView.setLoadingIndicator(false)


            }

        })

    }

    override fun loadPlayers(team: Team) {

        homePageView.showPlayers(team)
    }

    private fun getTeamsByLeagues(idLeague: String) {

        teamRepository.getTeams(idLeague,object :TeamDataSource.LoadTeamsCallback{
            override fun onTeamsLoaded(teams: List<Team>) {

                homePageView.showTeams(teams)
            }

            override fun onDataNotAvailable(code: Int) {

                homePageView.showNoTasks()

            }


        })
    }
}