package noblur.com.fdj.homepage


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.util.Util
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*

import noblur.com.fdj.R
import noblur.com.fdj.data.League
import noblur.com.fdj.data.Team
import noblur.com.fdj.playerdetail.PlayerDetailActivity
import noblur.com.fdj.utils.Util.Companion.hideKeyBoard

/**
 * A simple [Fragment] subclass.
 */
class HomePageFragment : Fragment(),HomePageContract.View, SearchView.OnQueryTextListener,
    DeviceItemTeamActionsListener {

    override lateinit var presenter: HomePageContract.Presenter
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var _teams: MutableList<Team>

    companion object {

        fun newInstance() = HomePageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_home_page, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _teams = mutableListOf()
        teamAdapter = TeamAdapter(context!!,_teams,this)

        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, 0))
            layoutManager = GridLayoutManager(activity,2)
            adapter = teamAdapter


        }

        search_view_team.setOnQueryTextListener(this)
        refresh_layout.setOnRefreshListener {
            setLoadingIndicator(false)
        }
    }
    override fun setLoadingIndicator(active: Boolean) {

        val root = view ?: return
        with(root.findViewById<SwipeRefreshLayout>(R.id.refresh_layout)) {
            // Make sure setRefreshing() is called after the layout is done with everything else.
            post { isRefreshing = active }
        }
    }


    override fun showTeams(teams: List<Team>) {

        _teams.clear()
        _teams.addAll(teams)
        teamAdapter.notifyDataSetChanged()
    }

    override fun showPlayers(team: Team) {

        val intent = Intent(context, PlayerDetailActivity::class.java).apply {
            putExtra(PlayerDetailActivity.EXTRA_TEAM_STR, team.strTeam)
        }
        startActivity(intent)
    }


    override fun showNoTasks() {
        _teams.clear()
        teamAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        presenter.loadTeamsOfLeague(query!!)
        hideKeyBoard(activity)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

       // presenter.loadTeamsOfLeague(newText!!)

        return true

    }

    override fun onTeamClicked(team: Team) {

        presenter.loadPlayers(team)

    }


}
