package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game

class FavouriteGamesListAdapter(val itemClicked: ItemClicked) :
    androidx.recyclerview.widget.ListAdapter<FavouriteGameDto, FavouriteGamesListAdapter.ViewHolder>(
        GameDiff
    ) {

    interface ItemClicked {
        fun onClick(author: String)
    }

    object GameDiff : DiffUtil.ItemCallback<FavouriteGameDto>() {
        override fun areItemsTheSame(oldItem: FavouriteGameDto, newItem: FavouriteGameDto): Boolean {
            return (oldItem.title == newItem.title) &&
                    (oldItem.salePrice == newItem.salePrice)
        }

        override fun areContentsTheSame(oldItem: FavouriteGameDto, newItem: FavouriteGameDto): Boolean {
            val ancientTitle: String = oldItem.title
            val newTitle: String = newItem.title
            return ancientTitle == newTitle
        }

    }

    class ViewHolder(val binding: SaleGameItemBinding, callback: ItemClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: FavouriteGameDto) {
            binding.tvGameTitle.text = game.title
            binding.tvGamePrice.text = game.salePrice + " $"

            Glide
                .with(binding.imageView)
                .load(game.thumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
        }

        init {
            binding.root.setOnClickListener {
                callback.onClick("Hola")
                Log.d("Pulsación", binding.tvGameTitle.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
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




