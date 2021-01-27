package saktidatt.myapplication.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class MyAppReceiver : BroadcastReceiver() {
    var context: Context? = null
    override fun onReceive(context: Context?, intent: Intent) {
        this.context = context

        if (intent.action == Intent.ACTION_PACKAGE_ADDED || intent.action ==
                Intent.ACTION_PACKAGE_REMOVED) {
            context?.let {
           
          context.startActivity(Intent(context,MainActivity::class.java))

            }
        }
    }
}