package noblur.com.fdj

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import noblur.com.fdj.data.source.local.FdjDatabase
import noblur.com.fdj.data.source.local.LeagueLocalDataSource
import noblur.com.fdj.data.source.local.PlayerLocalDataSource
import noblur.com.fdj.data.source.local.TeamLocalDataSource
import noblur.com.fdj.data.source.remote.*
import noblur.com.fdj.data.source.repository.LeagueRepository
import noblur.com.fdj.data.source.repository.PlayerRepository
import noblur.com.fdj.data.source.repository.TeamRepository
import noblur.com.fdj.utils.AppExecutors

object Injection {



    fun provideLeagueRepository(context: Context): LeagueRepository {
        val database = FdjDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(FdjService::class.java)

        return LeagueRepository.getInstance(
            LeagueRemoteDataSource.getInstance(CompositeDisposable(),api),
            LeagueLocalDataSource.getInstance(AppExecutors(), database.leagueDao()))
    }

    fun provideTeamRepository(context: Context): TeamRepository {
        val database = FdjDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(FdjService::class.java)

        return TeamRepository.getInstance(
            TeamRemoteDataSource.getInstance(CompositeDisposable(),api),
            TeamLocalDataSource.getInstance(AppExecutors(), database.teamDao()))
    }

    fun providePlayerRepository(context: Context): PlayerRepository {
        val database = FdjDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(FdjService::class.java)

        return PlayerRepository.getInstance(
            PlayerRemoteDataSource.getInstance(CompositeDisposable(),api),
            PlayerLocalDataSource.getInstance(AppExecutors(), database.playerDao()))
    }

}