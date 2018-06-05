package ru.iteye.androidlivecourseapp.repositories

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import ru.iteye.androidlivecourseapp.data.database.firebasedatabase.FirebaseDatabase
import ru.iteye.androidlivecourseapp.domain.global.repositories.UserRepository
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskDatabaseListener
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class UserRepositoryImpl: UserRepository {

    companion object {
        val TAG = "*** UserRepositoryImpl"
    }

    val db = FirebaseDatabase()

    /**
     * .../users/{UID} [allow r/w UID == UserID]
     *              snType => Object ( vk: String, fb: String )
     *              email: String
     *              phone: String
     *              firstName: String
     *              lastName: String
     *              sex: String
     *              birth: Timestamp
     *              country: String
     *              city: String
     *              location: Geopoint
     *              ----
     *              Сюда пишем при авторизации через социалку в первый раз
     */

    /**
     * .../likes/{UID} [allow r/w UID == UserID]
     *              to: String
     *              dateLike: Timestamp
     *              ---
     *              Сюда пишем когда пользователь лайкнул
     */

    /**
     * .../premiums/{UID} [allow r UID == UserID]
     *              premium: Boolean
     *              premiumStart: Timestamp
     *              premiumTill: Timestamp
     *              ---
     *              Сюда пишет функция Firebase при успешной оплате
     */

    /**
     * .../admins/{UID} [allow r UID == UserID]
     *              isAdmin: Boolean
     *              ---
     *              Сюда пишем один раз руками если это будет надо
     */

    /**
     *
     * Запрос на соотвествтеие лайков
     * Смотрим всех кого мы лайкнули и проверяем только их коллекции лайков на свой UID
     *
     */

    inner class userObjectsnType(vk: Boolean, fb: Boolean) {}

    override fun createUser(userData: HashMap<String, Any>): Observable<Boolean> {
        val user = HashMap<String, Any>()
        user["snType"] = userObjectsnType(false, false)
        user["email"] = ""
        user["phone"] = ""
        user["firstName"] = ""
        user["lastName"] = ""
        user["sex"] = ""
        user["birth"] = Date().time
        user["country"] = ""
        user["city"] = ""
        user["location"] = "0.0000, 0,0000"

        var keyUserDataValue: Any?
        for(key in user) {
            if (userData.containsKey(key.key) && userData[key.key] != null) {
                keyUserDataValue = userData[key.key]
                if (keyUserDataValue!=null) key.setValue(keyUserDataValue)
            }
        }


        return Observable.create<Boolean> { subscriber ->
            try {
                db.write("users", user, object: TaskDatabaseListener {
                    override fun onError(exception: Exception?) {
                        subscriber.onError(exception)
                    }
                    override fun onWriteSuccess(result: String) {
                        subscriber.onNext(true)
                        subscriber.onComplete()
                    }
                    override fun onReadSuccess(result: HashMap<String, Any>) {
                    }
                })
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }

    override fun getUser(): Observable<HashMap<String, Any>>{
        return Observable.create<HashMap<String, Any>> {
            try {
                db.read("users", object : TaskDatabaseListener {
                    override fun onError(exception: Exception?) {
                        it.onError(exception)
                    }
                    override fun onReadSuccess(result: HashMap<String, Any>) {
                        onReadSuccess(result)
                    }
                    override fun onWriteSuccess(result: String) {
                    }
                })
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun setLike(to: String) : Observable<Boolean> {
        val collection = "likes/$to"

        val fAuth = FirebaseAuth.getInstance()
        val userId = fAuth.currentUser?.uid

        return Observable.create<Boolean> { subscriber ->
            try {
                if (userId==null) {
                    subscriber.onError(Exception(ErrorsTypes.USER_IS_NULL.toString()))
                } else {

                    val likeHash = HashMap<String, Any>()
                    likeHash["from"] = userId
                    likeHash["dateLike"] = Date().time

                    db.write(collection, likeHash, object : TaskDatabaseListener {
                        override fun onError(exception: Exception?) {
                            subscriber.onError(exception)
                        }

                        override fun onWriteSuccess(result: String) {
                            subscriber.onNext(true)
                            subscriber.onComplete()
                        }

                        override fun onReadSuccess(result: HashMap<String, Any>) {
                        }
                    })

                }
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }

    override fun getLikes(owner: String) : Observable<HashMap<String, Any>>{

        return Observable.create<HashMap<String, Any>> {
            try {
                val collection = "likes/$owner"

                db.read(collection, object : TaskDatabaseListener {
                    override fun onError(exception: Exception?) {
                        it.onError(exception)
                    }
                    override fun onReadSuccess(result: HashMap<String, Any>) {
                        onReadSuccess(result)
                    }
                    override fun onWriteSuccess(result: String) {
                    }
                })

            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun checkLikesCross() : Observable<ArrayList<String>>{
        val fAuth = FirebaseAuth.getInstance()
        val userId = fAuth.uid

        return Observable.create<ArrayList<String>> {
            if (userId==null) {
                it.onError(Exception(ErrorsTypes.USER_IS_NULL.toString()))
            } else {
                val iLikedBy = getLikes(userId)
                iLikedBy.forEach { i ->

                }

            }
        }


    }

    override fun checkPremium() : Observable<Boolean>{

        val fAuth = FirebaseAuth.getInstance()
        val userId = fAuth.currentUser?.uid

        return Observable.create<Boolean> {
            try {

                if (userId==null) {
                    it.onError(Exception(ErrorsTypes.USER_IS_NULL.toString()))
                } else {

                    val collection = "premiums/$userId"

                    db.read(collection, object : TaskDatabaseListener {
                        override fun onError(exception: Exception?) {
                            it.onError(exception)
                        }
                        override fun onReadSuccess(result: HashMap<String, Any>) {
                            if (result.containsKey("isPremium") && result["isPremium"]==true) {
                                it.onNext(true)
                                it.onComplete()
                            } else {
                                it.onNext(false)
                                it.onComplete()
                            }
                        }
                        override fun onWriteSuccess(result: String) {
                        }
                    })

                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun checkAdmin() : Observable<Boolean>{
        val fAuth = FirebaseAuth.getInstance()
        val userId = fAuth.currentUser?.uid

        return Observable.create<Boolean> {
            try {

                if (userId==null) {
                    it.onError(Exception(ErrorsTypes.USER_IS_NULL.toString()))
                } else {

                    val collection = "admins/$userId"

                    db.read(collection, object : TaskDatabaseListener {
                        override fun onError(exception: Exception?) {
                            it.onError(exception)
                        }
                        override fun onReadSuccess(result: HashMap<String, Any>) {
                            if (result.containsKey("isAdmin") && result["isAdmin"]==true) {
                                it.onNext(true)
                                it.onComplete()
                            } else {
                                it.onNext(false)
                                it.onComplete()
                            }
                        }
                        override fun onWriteSuccess(result: String) {
                        }
                    })

                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }



}