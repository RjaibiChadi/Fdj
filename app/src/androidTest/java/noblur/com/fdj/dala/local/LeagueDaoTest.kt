package noblur.com.fdj.dala.local

import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import noblur.com.fdj.data.League
import noblur.com.fdj.data.source.local.FdjDatabase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) class LeagueDaoTest {

        private lateinit var database :FdjDatabase

        @Before fun initDb(){

                database = Room.inMemoryDatabaseBuilder(androidx.test.InstrumentationRegistry.getContext(),
                            FdjDatabase::class.java).build()

        }

        @After fun closeDb() = database.close()

        @Test fun insertLeagueAndGetById(){

                database.leagueDao().insertLeague(DEFAULT_LEAGUE)

            val loaded =database.leagueDao().getLeagueById(DEFAULT_LEAGUE.id)

            MatcherAssert.assertThat(loaded!!.id, `is`(1))


        }
    @Test fun deleteAllLeagueAndGettingTasks() {
        // Given a league inserted
        database.leagueDao().insertLeague(DEFAULT_LEAGUE)

        // When deleting all league
        database.leagueDao().deleteAllLeagues()

        // When getting the leagues
        val leagues = database.leagueDao().getLeagues()

        // The list is empty
        assertThat(leagues.size, `is`(0))
    }




    companion object {

        private val DEFAULT_ID = 1
        private val DEFAULT_STR_LEAGUE = "la liga"
        private val DEFAULT_ID_LEAGUE = "1234"
        private val DEFAULT_STR_SPORT = "soccer"
        private val DEFAULT_STR_LEAGUE_ALTERNATE = "la liga"
        private val DEFAULT_LEAGUE = League(DEFAULT_ID,DEFAULT_ID_LEAGUE,DEFAULT_STR_LEAGUE,DEFAULT_STR_SPORT, DEFAULT_STR_LEAGUE_ALTERNATE)
    }
}