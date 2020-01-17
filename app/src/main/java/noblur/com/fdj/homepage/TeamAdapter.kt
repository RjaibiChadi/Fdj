package noblur.com.fdj.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.team_item.view.*
import noblur.com.fdj.R
import noblur.com.fdj.data.Team


class TeamAdapter (val context: Context,val teams: MutableList<Team>, val listener: DeviceItemTeamActionsListener )
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val teamContainer = itemView.container_team

        val imageTeam = itemView.img_team
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.team_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team = teams[position]

        with(holder){


            teamContainer.tag = team
            Glide.with(context).load(team.strTeamBadge).into(imageTeam);
            teamContainer.setOnClickListener{
                listener.onTeamClicked(team)
            }
        }




    }
    override fun getItemCount(): Int {
        return teams.size
    }


}