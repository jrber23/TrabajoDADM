package dadm.jrbercan.trabajodadm.ui.moreGameInfo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.domain.model.GameDeals

class MoreGameInfoListAdapter(private val gameDeals: List<GameDeals>):
    RecyclerView.Adapter<MoreGameInfoListAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<TextView>(R.id.store_name)
        val price: TextView = view.findViewById<TextView>(R.id.store_game_price)
        val logo: ImageView = view.findViewById<ImageView>(R.id.store_logo)

    }

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_deals_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = gameDeals[position]
        holder.name.text = currentItem.name
        holder.price.text = currentItem.price
        Glide.
        with(holder.logo.context)
            .load("https://www.cheapshark.com/" + currentItem.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.logo)
        //Glide.with(context).load(currentItem.image).into(holder.logo)
    }

    override fun getItemCount(): Int {
        return gameDeals.size
    }
}