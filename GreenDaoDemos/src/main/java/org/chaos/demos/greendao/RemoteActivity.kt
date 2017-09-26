package org.chaos.demos.greendao

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_remote.*

/**
 * @author Chaos
 * *         22/05/2017
 */

class RemoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)

        var bill = getBill(1)
        Log.d(TAG, "bill = " + bill)
        bill = getBill(15)
        Log.d(TAG, "bill2 = " + bill)

        text.postDelayed({
            bill = getBill(1)
            Log.d(TAG, "bill3 = " + bill)
            bill = getBill(15)
            Log.d(TAG, "bill4 = " + bill)
        }, 4000)
    }

    private val bills: List<Bill>
        get() = DaoManager.billDao!!.loadAll()

    private fun getBill(id: Long?): Bill? {
        return DaoManager.billDao!!.load(id)
    }

    private fun saveBill(bill: Bill): Long? {
        if (DaoManager.billDao!!.hasKey(bill)) {
            DaoManager.billDao!!.update(bill)
            return bill.id
        } else {
            val row = DaoManager.billDao!!.insert(bill)
            return DaoManager.billDao!!.loadByRowId(row).id
        }
    }

    private fun deleteBill(id: Long?) {
        DaoManager.billDao!!.deleteByKey(id!!)
    }

    companion object {
        private val TAG = "RemoteActivity"
    }
}