package org.chaos.demos.greendao

import android.content.Context

/**
 * @author Chaos
 *         22/05/2017
 */
object DaoManager {

    var billDao: BillDao? = null

    fun initialize(context: Context) {
        billDao = DaoMaster(DaoMaster.DevOpenHelper(context, "bills.db").writableDb).newSession().billDao
    }
}