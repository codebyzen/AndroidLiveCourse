package ru.iteye.androidlivecourseapp.presentation.main_screen.fragments.overlap

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.iteye.androidlivecourseapp.R

class OverlapFragment: Fragment() {

    fun newInstance(): OverlapFragment {
        return OverlapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.overlap_screen, container, false)
    }

}