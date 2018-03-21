package ru.iteye.androidlivecourseapp.presentation.mvp.splash_screen

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.repositories.SplashRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.splash.SplashInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.ErrorsTypes


class SplashPresenter : BasePresenter<SplashView>() {

    private var interactor = SplashInteractor(SplashRepositoryImpl())

    private fun afterCheck(results: ErrorsTypes) {
        Log.d("***", "SplashPresenter -> afterAuthCheck")
        //TODO: при отсутствии пользователя показывается сначала тру потом фолс. Потом разберусь.
        Log.d("***", "SplashPresenter -> afterCheck -> results = " + results.toString())
        when (results) {
            ErrorsTypes.ALLOK -> getView()?.startFriendsListActivity()
            ErrorsTypes.AUTHERROR -> getView()?.startAuthChooseActivity() //TODO: убрать
            ErrorsTypes.GOOGLEPLAYERROR -> getView()?.googlePlayServiceError()
            ErrorsTypes.USERISNULL -> getView()?.startAuthChooseActivity() //TODO: убрать
            ErrorsTypes.ERROR_USER_NOT_FOUND -> getView()?.startAuthChooseActivity()
        }
    }

    fun startupCheck() {
        Log.d("***", "SplashPresenter -> startupCheckList")

        disposables.add(
            interactor.startupCheck()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isCheckSuccess ->
                    Log.d("***", "SplashPresenter -> startupCheckList -> thread name: " + Thread.currentThread().name)
                    Log.d("***", "SplashPresenter -> startupCheckList -> observerListener -> observableAuthResult: " + isCheckSuccess.toString())
                    afterCheck(isCheckSuccess)
                })
        )

    }

}



