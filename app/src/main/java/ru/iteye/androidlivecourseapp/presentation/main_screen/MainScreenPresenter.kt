package ru.iteye.androidlivecourseapp.presentation.main_screen


import com.vk.sdk.api.*
import ru.iteye.androidlivecourseapp.presentation.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import ru.iteye.androidlivecourseapp.domain.SocialNetworks.SNInteractor
import ru.iteye.androidlivecourseapp.repositories.SNRepositoryImpl


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