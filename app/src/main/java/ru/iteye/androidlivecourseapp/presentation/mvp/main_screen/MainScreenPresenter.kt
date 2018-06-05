package ru.iteye.androidlivecourseapp.presentation.mvp.main_screen

import android.content.Context
import android.util.Log
import com.vk.sdk.api.*
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.SocialNetworks.Friend
import ru.iteye.androidlivecourseapp.domain.SocialNetworks.SNInteractor
import ru.iteye.androidlivecourseapp.repositories.SNRepositoryImpl
import kotlin.collections.ArrayList


class MainScreenPresenter: BasePresenter<MainScreenView>() {

    private val TAG = "***MainScreenPresenter"

    private var interactor = SNInteractor(SNRepositoryImpl())

    fun hello(){
        //Get user info
        VKApi.users().get().executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                val user = (response!!.parsedModel as VKList<VKApiUser>)[0]
                getView()?.showMessage("Привет ${user.first_name} ${user.last_name}")
            }
        })
    }




}