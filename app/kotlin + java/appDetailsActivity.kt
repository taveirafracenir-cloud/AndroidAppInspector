package com.androidappinspector

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AppDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_details)

        // Pega as informações passadas pela Activity anterior
        val appName = intent.getStringExtra("APP_NAME")
        val appPackage = intent.getStringExtra("APP_PACKAGE")
        val permissions = intent.getStringArrayListExtra("APP_PERMISSIONS")

        // Encontra as views no layout
        val appNameTextView: TextView = findViewById(R.id.detail_app_name)
        val appPackageTextView: TextView = findViewById(R.id.detail_app_package)
        val appPermissionsTextView: TextView = findViewById(R.id.detail_permissions_list)
        val appIconImageView: ImageView = findViewById(R.id.detail_app_icon)

        // Define os valores das views com as informações do aplicativo
        appNameTextView.text = appName
        appPackageTextView.text = appPackage

        // Exibe o ícone do aplicativo
        try {
            val iconDrawable = packageManager.getApplicationIcon(appPackage!!)
            appIconImageView.setImageDrawable(iconDrawable)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Converte a lista de permissões em uma única string e exibe
        if (!permissions.isNullOrEmpty()) {
            val permissionsText = permissions.joinToString("\n")
            appPermissionsTextView.text = permissionsText
        } else {
            appPermissionsTextView.text = "Nenhuma permissão encontrada."
        }
    }
}
