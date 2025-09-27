private fun getInstalledApps(): List<AppInfo> {
        val packageManager = packageManager
        val apps = mutableListOf<AppInfo>()
        val packages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS)

        for (packageInfo in packages) {
            // Verifica se o aplicativo é do sistema
            val isSystemApp = (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0

            if (isSystemApp) {
                val appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
                val packageName = packageInfo.packageName
                val icon = packageManager.getApplicationIcon(packageInfo.applicationInfo)

                val permissions = packageInfo.requestedPermissions?.toList() ?: emptyList()

                apps.add(AppInfo(appName, packageName, icon, permissions))
            }
        }
        return apps.sortedBy { it.appName.lowercase() }
    }
