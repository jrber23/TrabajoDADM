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
import com.bumptech.glide.Glide
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game
import okhttp3.*
import javax.inject.Inject

class LastSalesListAdapter(val itemClicked: ItemClicked, private val onFavoriteClicked: (Game) -> Unit, private val onGameClicked: (Game) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Game, LastSalesListAdapter.ViewHolder>(
        LastSalesListAdapter.GameDiff
    ) {
    interface ItemClicked {
        fun onClick(game: Game)
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
            if (game.price == "0.00") { binding.tvGamePrice.text = binding.tvGamePrice.context.getString(R.string.free_sale) }
            else { binding.tvGamePrice.text = game.price + " $" }

            Glide.
            with(binding.imageView.context)
                .load(game.thumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
        }

        init {
            binding.root.setOnClickListener {

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

        val imageViewFav =  holder.itemView.findViewById<ImageView>(R.id.iconFavImageView)

        imageViewFav.setOnClickListener {
            onFavoriteClicked(getItem(position))
        }

        holder.itemView.setOnClickListener {
            onGameClicked(getItem(position))
        }
    }

}