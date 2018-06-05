package ru.iteye.androidlivecourseapp.presentation.ui.main_screen.fragments.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.iteye.androidlivecourseapp.R

class SettingsFragment: Fragment() {

    fun newInstance(): SettingsFragment {
        return SettingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings_screen, container, false)
    }
}