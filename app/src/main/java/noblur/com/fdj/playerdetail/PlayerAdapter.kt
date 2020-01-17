package noblur.com.fdj.playerdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.player_item.view.*

import kotlinx.android.synthetic.main.team_item.view.*
import kotlinx.android.synthetic.main.team_item.view.container_team
import kotlinx.android.synthetic.main.team_item.view.img_team
import noblur.com.fdj.R
import noblur.com.fdj.data.Player
import noblur.com.fdj.data.Team


class PlayerAdapter (val context: Context, val players: List<Player> )
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val teamContainer = itemView.container_team

        val playerImg = itemView.player_img

        val playerName = itemView.player_name
        val playerPosition = itemView.player_position
        val playerBirthday = itemView.player_birthday
        val playerPrice = itemView.player_price
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.player_item,parent,false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val player = players[position]

        with(holder){


            teamContainer.tag = player
            Glide.with(context).load(player.strThumb).into(playerImg)
            playerName.text = player.strPlayer
            playerPosition.text = player.strPosition
            playerBirthday.text = player.dateBorn
            playerPrice.text = player.strSigning
        }




    }
    override fun getItemCount(): Int {
        return players.size
    }


}