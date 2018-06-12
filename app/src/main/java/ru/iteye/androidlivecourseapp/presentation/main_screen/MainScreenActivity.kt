package ru.iteye.androidlivecourseapp.presentation.main_screen

import android.os.Bundle
import android.util.Log
import ru.iteye.androidlivecourseapp.R
import ru.iteye.androidlivecourseapp.presentation.global.BaseActivity
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import kotlinx.android.synthetic.main.main_screen.*
import ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.friends_list.FriendListFragment
import ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.overlap.OverlapFragment
import ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.settings.SettingsFragment


class MainScreenActivity: BaseActivity(), MainScreenView {

    private val TAG = "***MainScreenActivity"

    private val friendsListPresenter = MainScreenPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        friendsListPresenter.setView(this)

        friendsListPresenter.hello()

        val bottomNavigationView = main_screen_menu_bottom

        //TODO: убрать текст в стринги
        bottomNavigationView.setOnNavigationItemSelectedListener(
                object : BottomNavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        var selectedFragment: Fragment? = null
                        when (item.itemId) {
                            R.id.action_friends -> {
                                showMessage("Друзья", true)
                                selectedFragment = FriendListFragment().newInstance()
                            }
                            R.id.action_connections -> {
                                showMessage("Совпадения", true)
                                selectedFragment = OverlapFragment().newInstance()
                            }
                            R.id.action_settings -> {
                                showMessage("Настройки", true)
                                selectedFragment = SettingsFragment().newInstance()
                            }
                        }
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.main_screen_frame, selectedFragment)
                        transaction.commit()
                        return true
                    }
                })

        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_screen_frame, FriendListFragment().newInstance())
        transaction.commit()

        bottomNavigationView.menu.getItem(1).isChecked = true

        Log.d(TAG, "MainScreenActivity CREATE")
    }




    override fun showMessage(msg: String) {
        showMessage(msg, true)
    }

}