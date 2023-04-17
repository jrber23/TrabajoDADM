package dadm.jrbercan.trabajodadm.ui.lastSales

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game
import okhttp3.*

class LastSalesListAdapter(val itemClicked: ItemClicked, private val onFavoriteClicked: (SaleGameDto) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<SaleGameDto, LastSalesListAdapter.ViewHolder>(
        LastSalesListAdapter.GameDiff
    ) {
    interface ItemClicked {
        fun onClick(thumb: String)
    }

    object GameDiff : DiffUtil.ItemCallback<SaleGameDto>() {
        override fun areItemsTheSame(oldItem: SaleGameDto, newItem: SaleGameDto): Boolean {
            return (oldItem.title == newItem.title) &&
                    (oldItem.salePrice == newItem.salePrice)
        }

        override fun areContentsTheSame(oldItem: SaleGameDto, newItem: SaleGameDto): Boolean {
            val ancientTitle: String = oldItem.title
            val newTitle: String = newItem.title
            return ancientTitle == newTitle
        }

    }

    class ViewHolder(val binding: SaleGameItemBinding, callback: ItemClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: SaleGameDto) {
            binding.tvGameTitle.text = game.title
            binding.tvGamePrice.text = game.salePrice + " $"
            // binding.imageView.setImageURI(game.thumb.toUri())
        }

        init {
            binding.root.setOnClickListener {
                callback.onClick(binding.tvGameTitle.text.toString())
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
        //holder.bind(getItem(position))
        val sale = getItem(position)
        holder.bind(sale)

        holder.itemView.findViewById<ImageView>(R.id.iconFavImageView).setOnClickListener {
            //Log.d("FAVICON", "CLICKED")
            onFavoriteClicked(getItem(position))
        }
    }

}