package ru.iteye.androidlivecourseapp.presentation.mvp.reg_email

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.repositories.RegRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.reg.RegInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpectionUtil


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
                        afterRegistration()
                    }, { error ->
                        error.printStackTrace()
                        afterRegistrationError(error)
                    })
        )


    }

    private fun afterRegistrationError(error: Throwable) {
        Log.d("***", "RegEmailPresenter -> afterRegistrationError")
        Log.d("***", "RegEmailPresenter -> afterRegistrationError -> error.message: " + error.message.toString())
        if (error is FirebaseExpectionUtil) {
            Log.d("***", "afterRegistrationError -> afterRegistrationError -> error is FirebaseExpectionUtil")
            when (error.type) {
                ErrorsTypes.ERROR_USER_NOT_FOUND -> onUserRegError()
                ErrorsTypes.ERROR_EMAIL_ALREADY_IN_USE -> onUserAlreadyExist()
            }
        } else {
            Log.d("***", "RegEmailPresenter -> afterRegistrationError -> error is NOT FirebaseExpectionUtil")
            getView()?.showError(error.message!!, {})
        }
    }

    private fun onUserRegError(){
        Log.d("***", "afterRegistrationError -> onUserRegError")

    }

    private fun onUserAlreadyExist(){
        Log.d("***", "afterRegistrationError -> afterRegistration")
        getView()?.onUserAlreadyExist()
    }

    private fun afterRegistration() {
        Log.d("***", "afterRegistrationError -> afterRegistration")
        getView()?.onUserRegistered()
    }



}