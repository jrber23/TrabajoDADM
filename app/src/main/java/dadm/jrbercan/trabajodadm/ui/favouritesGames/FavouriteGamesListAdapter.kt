package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.SaleGameItemBinding
import dadm.jrbercan.trabajodadm.domain.model.Game

class FavouriteGamesListAdapter(val itemClicked: ItemClicked, private val onFavouriteClicked: (Game) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Game, FavouriteGamesListAdapter.ViewHolder>(
        GameDiff
    ) {

    interface ItemClicked {
        fun onClick(author: String)
    }

    object GameDiff : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return (oldItem.title == newItem.title)
                    //&& (oldItem.salePrice == newItem.salePrice)
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
            binding.tvGamePrice.text = game.price + " $"

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
        val game = getItem(position)
        holder.bind(game)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.iconFavImageView)
        if(game.favourite){
            imageView.setImageResource(R.drawable.baseline_favorite_24)
        }
        imageView.setOnClickListener{
            onFavouriteClicked(game)
        }
    }
}




