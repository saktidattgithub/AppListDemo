package saktidatt.myapplication.applistsdk

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo


object AppListObject {

    val appModelListGlobal:MutableList<AppModel> =
            ArrayList()

    fun getInstalledAppList(mContext: Context)
    {
        val appModelList:MutableList<AppModel> =
            ArrayList()
        val packageList1: MutableList<PackageInfo> =
            ArrayList()
        try {
            if (mContext != null) {
               var packageManager = mContext.getPackageManager()
                val packageList: List<PackageInfo> =
                    packageManager
                        .getInstalledPackages(0)

               for (cpi in packageList) {
                   var appModel = AppModel()
                    val b: Boolean = isSystemPackage(cpi)
                    if (!b) {
                        appModel.packageName = cpi.applicationInfo.packageName
                        appModel.appName = cpi.applicationInfo.loadLabel(packageManager).toString()
                        appModel.icon = cpi.applicationInfo.loadIcon(packageManager)
                       // appModel.mainActivity = cpi.applicationInfo.taskAffinity
                        appModel.versionName = cpi.versionName
                        appModel.versionCode = cpi.versionCode.toString()
                        appModelList.add(appModel)
                    }
                }
            }
        } catch (e: Exception) {
        }

        if(appModelListGlobal!=null && appModelListGlobal.size > 0)
        {
            appModelListGlobal.clear()
        }
        appModelListGlobal.addAll(appModelList.sortedBy { it.appName })

    }

    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    public fun getAppList(mContext: Context):MutableList<AppModel>
    {
        if(appModelListGlobal!=null && appModelListGlobal.size == 0){
            getInstalledAppList(mContext)
        }
        return appModelListGlobal
    }


}