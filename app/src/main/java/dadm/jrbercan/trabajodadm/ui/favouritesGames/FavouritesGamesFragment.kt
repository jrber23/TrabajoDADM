package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.FragmentFavouritesGamesBinding
import dadm.jrbercan.trabajodadm.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesGamesFragment : Fragment(R.layout.fragment_favourites_games) {
    private var _binding: FragmentFavouritesGamesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesGamesViewModel by viewModels()
    private val callback = object: FavouriteGamesListAdapter.ItemClicked {
        override fun onClick(author: String) {
            Log.d("Pulsacion", "Pulsacion")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesGamesBinding.bind(view)

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.favourite_games_options, menu)
            }

            override fun onPrepareMenu(menu: Menu) {
                // Hide or show the delete_all_favorite menu item based on whether the list is empty or not
                menu.findItem(R.id.delete_all_favorite)?.isVisible = viewModel.favouriteGames.value?.isNotEmpty() == true
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_all_favorite -> {
                        DeleteAllGamesDialogFragment().show(childFragmentManager, null)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val adapter: FavouriteGamesListAdapter = FavouriteGamesListAdapter(callback, ::onFavouriteClicked)
        binding.recyclerViewFavorites.adapter = adapter
        viewModel.favouriteGames.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true // Habilitar el gesto de swipe
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = binding.recyclerViewFavorites.getChildAdapterPosition(viewHolder.itemView)
                // viewModel.deleteGameAtPosition(position)
            }
        })
        touchHelper.attachToRecyclerView(binding.recyclerViewFavorites) //  asociar el ItemTouchHelper al RecyclerView y así que responda a los gestos de swipe
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun onFavouriteClicked(game : Game){
        viewModel.deleteGameFromFavourites(game)
    }



}