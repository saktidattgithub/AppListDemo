package saktidatt.myapplication.applistsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyAppReceiver : BroadcastReceiver() {
    var context: Context? = null
    override fun onReceive(context: Context?, intent: Intent) {
        this.context = context

        if (intent.action == Intent.ACTION_PACKAGE_ADDED || intent.action ==
                Intent.ACTION_PACKAGE_REMOVED) {
            context?.let { AppListObject.getInstalledAppList(it) }
        }
    }
}