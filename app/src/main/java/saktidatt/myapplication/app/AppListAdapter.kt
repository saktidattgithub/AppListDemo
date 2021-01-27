package saktidatt.myapplication.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import saktidatt.myapplication.app.databinding.RecyclerItemBinding
import saktidatt.myapplication.applistsdk.AppModel


class AppListAdapter(
    var context: Context,
   val list: List<AppModel>
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var viewGroupParent: ViewGroup? = null

    var mActivity: Activity? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        var viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder? = null
        try {
            var view: View
            viewGroupParent = parent
            try {
                var mBinding: RecyclerItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item,
                    parent,
                    false
                )
                viewHolder = AppViewHolder(mBinding)
            } catch (e: Exception) {
            }

        } catch (e: Exception) {
        }

        return viewHolder!!
    }

    override fun getItemCount(): Int {

        if (list != null && list.size > 0) {
            return list.size
        } else {
            return 0
        }

    }



    override fun onBindViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {
        try {
            if (holder != null && (holder as AppViewHolder) != null && list != null && list.size > 0) {
                holder.itemBinding.AppName.text = list.get(position).appName
                holder.itemBinding.versionName.text = list.get(position).versionName
                holder.itemBinding.versionCode.text = list.get(position).versionCode
                holder.itemBinding.packageName.text = list.get(position).packageName
                Glide.with(context).load(list.get(position).icon).into(holder.itemBinding.iconImg)

                holder.itemBinding.mainContraint.setOnClickListener {
                    val intent: Intent? =
                        context.getPackageManager().getLaunchIntentForPackage(list.get(position).packageName!!)
                   context.startActivity(intent)
                }

            }
        } catch (e: Resources.NotFoundException) {
        } catch (e: Exception) {
        }
    }


}