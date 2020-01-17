package noblur.com.fdj.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model class for a League.
 *
 * @param id_league       title of the task
 * @param str_league description of the task
 * @param id          id of the task
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