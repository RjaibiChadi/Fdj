package noblur.com.fdj.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import noblur.com.fdj.Injection
import noblur.com.fdj.R
import noblur.com.fdj.utils.replaceFragmentInActivity
import noblur.com.fdj.utils.setupActionBar

class HomePageActivity : AppCompatActivity() {


    private lateinit var homePresenter: HomePagePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(R.id.toolbar) {
            setTitle(getString(R.string.home_page))

        }


        val homePageFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as HomePageFragment? ?: HomePageFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        homePresenter = HomePagePresenter(
            Injection.provideLeagueRepository(applicationContext),Injection.provideTeamRepository(applicationContext),
            homePageFragment)
    }
}
