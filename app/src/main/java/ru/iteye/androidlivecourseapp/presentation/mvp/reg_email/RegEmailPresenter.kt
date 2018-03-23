package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.repositories.RegRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.reg.RegInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes


class RegEmailPresenter: BasePresenter<RegEmailView>() {

    private var interactor = RegInteractor(RegRepositoryImpl())

    fun registration(email: String, password: String) {
        Log.d("***", "RegEmailPresenter -> signIn")

        disposables.add(
            interactor.regByMail(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ isRegSuccess ->
                        Log.d("***", "RegEmailPresenter -> signIn -> thread name: " + Thread.currentThread().name)
                        Log.d("***", "RegEmailPresenter -> observerListener -> observableRegResult: " + isRegSuccess.toString())
                        afterRegistration(isRegSuccess)
                    })
        )


    }


    private fun afterRegistration(isUserRegSuccess: ErrorsTypes) {

        Log.d("***", "RegEmailPresenter -> afterRegistration -> "+isUserRegSuccess.toString())

        when (isUserRegSuccess) {
            ErrorsTypes.ALLOK -> onUserRegistred()
            ErrorsTypes.AUTHERROR -> onUserRegError()
            ErrorsTypes.USERNOTAUTH -> onUserRegError()
        }
    }

    private fun onUserRegistred() {
        getView()?.onUserRegistered()
    }

    private fun onUserRegError() {
        getView()?.onFailedReg()
    }

}