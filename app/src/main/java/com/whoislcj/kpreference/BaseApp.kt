package com.whoislcj.kpreference

import android.app.Application

/**
 * Created by whoislcj on 2018/3/26.
 */
class BaseApp : Application() {

    companion object {
        lateinit var baseApp: BaseApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        baseApp = this;
    }


}