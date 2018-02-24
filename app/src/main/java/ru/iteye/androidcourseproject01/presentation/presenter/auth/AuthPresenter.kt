package ru.iteye.androidcourseproject01.presentation.presenter.auth

import ru.iteye.androidcourseproject01.domain.auth.AuthInteractor
import ru.iteye.androidcourseproject01.presentation.view.auth.AuthView
import ru.iteye.androidcourseproject01.repositories.auth.AuthRepositoryImpl

//TODO у нас ведь экран авторизации. Пользователь может авторизоваться как по мылу, так и по социалкам
// Пусть это будет общий презентер для авторизации
class AuthPresenter {
    private var interactor = AuthInteractor(AuthRepositoryImpl())
    private var view : AuthView? = null

    fun getView() : AuthView? {
        return view
    }

    fun setView(view : AuthView?){
        this.view = view
    }

    fun authByMail(mail : String, pass: String) {
        //TODO а тут презентер будет вызывать интерактор, который сам уже знает, кому сказать об авторизации
        //TODO надо разобраться с Rx, тогда тут будет subscribe и передача успеха во вьюшку
        interactor.authByMail(mail, pass)
    }
}
