package noblur.com.fdj.playerdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import noblur.com.fdj.Injection
import noblur.com.fdj.R
import noblur.com.fdj.utils.replaceFragmentInActivity
import noblur.com.fdj.utils.setupActionBar

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var playerDetailPresenter: PlayerDetailPresenter


    companion object {
        const val EXTRA_TEAM_STR = "TEAM_STR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        // Get the requested task id
        val teamStr = intent.getStringExtra(EXTRA_TEAM_STR)

        // Set up the toolbar.
        setupActionBar(R.id.toolbar) {
            setTitle(teamStr)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }



        val playerDetailFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as PlayerDetailFragment? ?: PlayerDetailFragment.newInstance(teamStr).also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        // Create the presenter
        playerDetailPresenter =  PlayerDetailPresenter(teamStr, Injection.providePlayerRepository(applicationContext),
            playerDetailFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
