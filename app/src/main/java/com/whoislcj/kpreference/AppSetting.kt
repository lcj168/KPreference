package com.whoislcj.kpreference


/**
 * Created by whoislcj on 2018/3/26.
 */
object AppSetting {

    /**
     * 默认的文件名
     */
    private val iPreference = PreferenceImpl();
    private const val USER_NAME = "user_name";


    fun setUserName(userName: String) {
        iPreference.put(USER_NAME, userName)
    }

    fun getUserName(): String {

        return iPreference.getString(USER_NAME);
    }

}