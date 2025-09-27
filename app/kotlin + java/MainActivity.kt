package com.androidappinspector 

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yourcompany.androidappinspector.AppInfo // Mude para o nome do seu pacote
import com.yourcompany.androidappinspector.AppListAdapter // Mude para o nome do seu pacote

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appList: List<AppInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.app_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        appList = getInstalledApps()
        val adapter = AppListAdapter(appList)
        recyclerView.adapter = adapter
    }

    private fun getInstalledApps(): List<AppInfo> {
        val packageManager = packageManager
        val apps = mutableListOf<AppInfo>()
        val packages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS)

        for (packageInfo in packages) {
            val appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
            val packageName = packageInfo.packageName
            val icon = packageManager.getApplicationIcon(packageInfo.applicationInfo)

            val permissions = packageInfo.requestedPermissions?.toList() ?: emptyList()

            apps.add(AppInfo(appName, packageName, icon, permissions))
        }

        return apps.sortedBy { it.appName.lowercase() }
    }
}
