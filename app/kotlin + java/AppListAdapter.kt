import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Ação de clique foi adicionada aqui
class AppListAdapter(private val appList: List<AppInfo>, private val onItemClick: (AppInfo) -> Unit) :
    RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appIcon: ImageView = view.findViewById(R.id.app_icon)
        val appName: TextView = view.findViewById(R.id.app_name)
        val appPackage: TextView = view.findViewById(R.id.app_package)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appInfo = appList[position]
        holder.appIcon.setImageDrawable(appInfo.icon)
        holder.appName.text = appInfo.appName
        holder.appPackage.text = appInfo.appPackage

        // Aqui está a nova lógica de clique
        holder.itemView.setOnClickListener {
            onItemClick(appInfo)
        }
    }

    override fun getItemCount() = appList.size
}
