package ru.iteye.androidcourseproject01

class AuthChoosePresenter(connectedActivity: AuthChooseActivity) {

    private val activity: AuthChooseActivity = connectedActivity

    fun startAuthEmailActivity() {
        activity.startAuthEmailActivity()
    }

}