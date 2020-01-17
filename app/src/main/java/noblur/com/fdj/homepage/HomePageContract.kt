package noblur.com.fdj.homepage

import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Team

interface HomePageContract {

    interface View : BaseView<Presenter> {


        fun setLoadingIndicator(active: Boolean)


        fun showTeams(teams: List<Team>)

        fun showPlayers(team: Team)

        fun showNoTasks()


    }

    interface Presenter : BasePresenter {

        fun loadLeagues()

        fun loadTeamsOfLeague(query:String)

        fun loadPlayers(team: Team)

    }

}