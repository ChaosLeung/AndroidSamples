package org.chaos.demos.greendao

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var bill = Bill(1, true, "Test", 35.5f, System.currentTimeMillis(), "Note")
//        saveBill(bill)
//        var bill = getBill(1)
        bill.price = 233F
        bill.category = "Main"
        bill = getBill(saveBill(bill))
        Log.d(TAG, "bill = " + bill)

        bill = Bill(15, true, "Test", 35.5f, System.currentTimeMillis(), "Note")
        Log.d(TAG, "bill5 = " + bill)
        bill = getBill(saveBill(bill))

//        startActivity(Intent(this, RemoteActivity::class.java))

        text.postDelayed({
            Log.d(TAG, "bill2 = " + bill)
            bill.price = 450f
            bill = getBill(saveBill(bill))
            Log.d(TAG, "bill3 = " + bill)

            bill = getBill(1)
            bill.price = 800F
            bill.category = "Remote"
            bill = getBill(saveBill(bill))
            Log.d(TAG, "bill4 = " + bill)
        }, 3000)
    }

    private val bills: List<Bill>
        get() = DaoManager.billDao!!.loadAll()

    private fun getBill(id: Long?): Bill {
        return DaoManager.billDao!!.load(id)
    }

    private fun saveBill(bill: Bill): Long? {
        if (DaoManager.billDao!!.hasKey(bill)) {
            DaoManager.billDao!!.update(bill)
            return bill.id
        } else {
            val row = DaoManager.billDao!!.insert(bill)
            Log.d(TAG, "saveBill: row = " + row)
            return DaoManager.billDao!!.loadByRowId(row).id
        }
    }

    private fun deleteBill(id: Long?) {
        DaoManager.billDao!!.deleteByKey(id!!)
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
