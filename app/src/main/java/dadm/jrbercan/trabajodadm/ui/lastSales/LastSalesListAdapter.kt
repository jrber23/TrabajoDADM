package dadm.jrbercan.trabajodadm.ui.lastSales

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game
import java.util.*

class LastSalesListAdapter(val itemClicked: ItemClicked, private val gamesList: ArrayList<Game>) :
    androidx.recyclerview.widget.ListAdapter<Game, LastSalesListAdapter.ViewHolder>(
        LastSalesListAdapter.GameDiff
    ), Filterable {

    var gamesFilterList = ArrayList<Game>()

    init {
        gamesFilterList = gamesList
    }

    interface ItemClicked {
        fun onClick(author: String)
    }

    object GameDiff : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return (oldItem.title == newItem.title) &&
                    (oldItem.price == newItem.price)
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            val ancientTitle: String = oldItem.title
            val newTitle: String = newItem.title
            return ancientTitle == newTitle
        }

    }

    class ViewHolder(val binding: SaleGameItemBinding, callback: ItemClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.tvGameTitle.text = game.title
            binding.tvGamePrice.text = game.price
        }

        init {
            binding.root.setOnClickListener {
                callback.onClick("Hola")
                Log.d("Pulsación", binding.tvGameTitle.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LastSalesListAdapter.ViewHolder(
            SaleGameItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClicked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // holder.bind(getItem(position))
        val selectTitleGameTextView =
            holder.itemView.findViewById<TextView>(R.id.tvGameTitle)
        selectTitleGameTextView.text = gamesFilterList[position].title

        val selectPriceGameTextView =
            holder.itemView.findViewById<TextView>(R.id.tvGamePrice)
        selectPriceGameTextView.text = gamesFilterList[position].price
    }

    override fun getItemCount(): Int {
        return gamesFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    gamesFilterList = gamesList
                } else {
                    val resultList = ArrayList<Game>()
                    for (row in gamesList) {
                        if (row.title.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    gamesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = gamesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                gamesFilterList = results?.values as ArrayList<Game>
                notifyDataSetChanged()
            }

        }
    }

}