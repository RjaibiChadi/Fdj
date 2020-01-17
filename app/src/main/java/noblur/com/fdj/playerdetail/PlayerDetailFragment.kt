package noblur.com.fdj.playerdetail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home_page.*

import noblur.com.fdj.R
import noblur.com.fdj.data.Player
import noblur.com.fdj.homepage.HomePageContract
import noblur.com.fdj.homepage.TeamAdapter

/**
 * A simple [PlayerDetailFragment] players more information.
 */
class PlayerDetailFragment : Fragment(),PlayerDetailContract.View {

    override lateinit var presenter: PlayerDetailContract.Presenter
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var _players: MutableList<Player>
    companion object {

        private val ARGUMENT_TEAM_STR = "TEAM_STR"


        fun newInstance(taskId: String?) =
            PlayerDetailFragment().apply {
                arguments = Bundle().apply { putString(ARGUMENT_TEAM_STR, taskId) }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _players = mutableListOf()
        playerAdapter = PlayerAdapter(context!!,_players)

        recyclerView.apply {

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter

        }
    }


    override fun showPlayers(player: List<Player>) {

        _players.clear()
        _players.addAll(player)
        playerAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()


    }




}
