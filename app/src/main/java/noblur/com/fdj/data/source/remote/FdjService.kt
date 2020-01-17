package noblur.com.fdj.data.source.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import noblur.com.fdj.data.*
import retrofit2.http.*

interface FdjService {

    @GET("all_leagues.php")
    fun getLeagues() : Observable<Leagues>

    @GET("lookup_all_teams.php?")
    fun getTeamsByLeague(@Query("id") id: String) : Observable<Teams>

    @GET("searchplayers.php?")
    fun getPlayersByTeam(@Query("t") t: String) : Observable<Players>
}