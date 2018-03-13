package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.repositories.RegRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.reg.RegInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter


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


    private fun afterRegistration(isUserRegSuccess: Boolean?) {

        Log.d("***", "RegEmailPresenter -> afterRegistration -> "+isUserRegSuccess.toString())

        when (isUserRegSuccess) {
            true -> onUserRegistred()
            false -> onUserRegError()
            null -> onFirebaseRegError()
        }
    }

    private fun onFirebaseRegError(){
        getView()?.onFailedFirebaseReg()
    }

    private fun onUserRegistred() {
        getView()?.onSuccessReg()
    }

    private fun onUserRegError() {
        getView()?.onFailedReg()
    }

}