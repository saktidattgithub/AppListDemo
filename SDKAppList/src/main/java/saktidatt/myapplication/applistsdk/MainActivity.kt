package saktidatt.myapplication.applistsdk

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var myAppReceiver:MyAppReceiver?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAppReceiver = MyAppReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addDataScheme("package")
        registerReceiver(myAppReceiver, intentFilter)
      var packageinfolist =   AppListObject.getInstalledAppList(this)

    }


    override fun onDestroy() {
        super.onDestroy()
        if(myAppReceiver!=null) {
            unregisterReceiver(myAppReceiver)
        }
    }


}