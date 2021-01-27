package saktidatt.myapplication.app

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import saktidatt.myapplication.app.databinding.ActivityMainBinding
import saktidatt.myapplication.applistsdk.AppListObject
import saktidatt.myapplication.applistsdk.AppModel
import saktidatt.myapplication.applistsdk.MyAppReceiver

class MainActivity : AppCompatActivity() {
    var activityMainBinding : ActivityMainBinding?=null
    var listAppmodel:MutableList<AppModel>?=null
    var adapter:AppListAdapter?=null
    var myAppReceiver: MyAppReceiver?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,null,false)
        setContentView(activityMainBinding?.root)

        registerReceiver()

        listAppmodel = AppListObject.getAppList(this)

        if(listAppmodel!=null && listAppmodel!!.size>0)
        {
            adapterInitialize(listAppmodel!!)
            activityMainBinding?.editQuery!!.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                    try {


                        if (s.toString().length == 0) {
                            listAppmodel = AppListObject.getAppList(applicationContext)
                            if(listAppmodel!=null && listAppmodel!!.size >0) {

                                    adapterInitialize(listAppmodel!!)
                            }
                        }
                        if (s.toString().length >= 3) {
                            if(listAppmodel!=null && listAppmodel!!.size >0) {

                                var filterlist = listAppmodel?.filter { it.appName!!.contains(s.toString())}
                               if(filterlist!=null && filterlist.size >0)
                                adapterInitialize(filterlist!! as MutableList<AppModel>)
                            }

                        }

                    } catch (e: Exception) {
                    }

                }
            })
        }

    }

    fun registerReceiver()
    {
        myAppReceiver = MyAppReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addDataScheme("package")
        registerReceiver(myAppReceiver, intentFilter)
    }

    fun adapterInitialize(list : MutableList<AppModel>)
    {
        try {
            adapter = AppListAdapter(this,list!!)
            activityMainBinding?.appRecycler!!.adapter = adapter

            var mLinearLayoutManager = object : LinearLayoutManager(this) {
                override fun supportsPredictiveItemAnimations(): Boolean {
                    return false
                }
            }
            mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            activityMainBinding?.appRecycler!!.layoutManager = mLinearLayoutManager
        } catch (e: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(myAppReceiver!=null) {
            unregisterReceiver(myAppReceiver)
        }
    }
}