package ru.iteye.androidlivecourseapp.presentation.mvp.friends_list

import android.content.Context
import android.util.Log
import com.vk.sdk.api.*
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKList
import ru.iteye.androidlivecourseapp.utils.Friend
import java.util.*



class FriendsListPresenter: BasePresenter<FriendsListView>() {

    private val TAG = "***FriendsListPresenter"

    fun getAge(birthDate: Calendar): Int {

        val today = Calendar.getInstance()
        var age = 0

        if (birthDate.after(today)) {
            throw IllegalArgumentException("Can't be born in the future")
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3 || birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH)) {
            age--

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if (birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH) && birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) {
            age--
        }

        return age
    }


    fun hello(){
        //Get user info
        VKApi.users().get().executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                val user = (response!!.parsedModel as VKList<VKApiUser>)[0]
                getView()?.showMessage("Привет ${user.first_name} ${user.last_name}")
            }
        })
    }


    fun loadFriends(ctx: Context) {
        Log.d(TAG, " -> loadFriends")
        VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"nickname, photo_50, photo_100, photo_200, photo_max, sex, bdate, city, country")).executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {

                val users = response!!.parsedModel as VKList<VKApiUserFull>
                val myDataset: ArrayList<Friend> = arrayListOf()
                for (user in users) {
                    val friendData: Friend = Friend()
                    friendData.firstName = user.first_name
                    friendData.lastName = user.last_name

                    // ищем самое большое изображение
                    var photo: String? = null
                    when {
                        !user.photo_max.equals("http://vk.com/images/camera_a.gif") -> photo = user.photo_max
                        !user.photo_200.equals("http://vk.com/images/camera_a.gif") -> photo = user.photo_200
                        !user.photo_100.equals("http://vk.com/images/camera_b.gif") -> photo = user.photo_100
                        else -> photo = user.photo_50
                    }

                    friendData.photoSmall = photo
                    friendData.snType = "vk"

                    if (user.city!==null) friendData.location = user.city.title
                    if (user.country!==null) friendData.location += ", " + user.country.title
                    if (friendData.location==null) friendData.location = ""

                    friendData.sex = user.sex

                    if (user.bdate!=="") {
                        val regex = """([\d]{1,2})\.([\d]{1,2})\.?([\d]+)?""".toRegex()
                        val matchResult = regex.find(user.bdate)
                        val (bday: String?, bmonth: String?, byear: String?) = matchResult!!.destructured

                        if (byear !== "") {
                            val bdateDate: Calendar = Calendar.getInstance()
                            bdateDate.set(byear.toInt(), bmonth.toInt(), bday.toInt())
                            val age: Int? = getAge(bdateDate)
                            friendData.age = age.toString()
                        } else {
                            friendData.age = ""
                        }
                    } else {
                        friendData.age = ""
                    }

                    myDataset.add(friendData)
                }
                getView()?.appendFriendsList(myDataset)
            }
        })




    }


}