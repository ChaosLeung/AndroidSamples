package org.chaos.demos.greendao

import android.app.Application

/**
 * @author Chaos
 *         22/05/2017
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DaoManager.initialize(this)
    }
}