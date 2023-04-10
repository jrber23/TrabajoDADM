package dadm.jrbercan.trabajodadm.ui.lastSales

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game

class LastSalesListAdapter(val itemClicked: ItemClicked) :
    androidx.recyclerview.widget.ListAdapter<Game, LastSalesListAdapter.ViewHolder>(
        LastSalesListAdapter.GameDiff
    ) {
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
            binding.tvGamePrice.text = game.price.toString()
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
        holder.bind(getItem(position))
    }

}