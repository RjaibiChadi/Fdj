package noblur.com.fdj.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model class for a Player.
 *
 * @param id                id of the player in database
 * @param strPlayer         name of the player
 * @param strPosition       position of the player
 * @param dateBorn          birthday of the player
 * @param strSigning        price of the player
 * @param strThumb          image of the player
 */
@Entity
data class Player(
                @PrimaryKey(autoGenerate = true)
                var id:Int,
                var strPlayer:String,
                  var strPosition:String,
                  var dateBorn:String,
                  var strSigning:String,
                  var strThumb:String)

data class Players(
    var player:List<Player>
)