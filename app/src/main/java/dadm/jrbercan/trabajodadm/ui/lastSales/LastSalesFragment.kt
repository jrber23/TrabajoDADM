package dadm.jrbercan.trabajodadm.ui.lastSales

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.favouritesGames.model.FavouriteGameDto
import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import dadm.jrbercan.trabajodadm.databinding.FragmentLastSalesBinding
import dadm.jrbercan.trabajodadm.ui.favouritesGames.DeleteAllGamesDialogFragment
import dadm.jrbercan.trabajodadm.ui.moreGameInfo.MoreGameInfoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastSalesFragment : Fragment(R.layout.fragment_last_sales),  AddToFavouritesDialogFragment.AddToFavouritesCallback {
    private var _binding: FragmentLastSalesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LastSalesViewModel by activityViewModels()
    private val callback = object : LastSalesListAdapter.ItemClicked {

        override fun onClick(game: FavouriteGameDto) {

        }

    }

    private val addToFavoritesCallback = object : AddToFavouritesDialogFragment.AddToFavouritesCallback {

        override fun onFavouriteSelected() {

        }

        override fun onCancelSelected() {

        }

    }

    private fun onGameClicked(game: SaleGameDto) {
        //Log.d("GAME INFO","ID: ${game.gameID}.")
        val intent = Intent(context,MoreGameInfoActivity::class.java)
            .putExtra("GAME_TITLE",game.title)
            .putExtra("GAME_ID",game.gameID)
            .putExtra("GAME_STEAM_ID",game.steamAppID)
            .putExtra("GAME_THUMB",game.thumb)
        startActivity(intent)
    }

    private fun onFavoriteClicked(game: SaleGameDto) {
        val dialogAddFavFragment = AddToFavouritesDialogFragment()
        dialogAddFavFragment.callback = this // Set the callback to this LastSalesFragment instance
        dialogAddFavFragment.show(childFragmentManager, null)
    }

    override fun onFavouriteSelected() {
        val dialogSetAlertFragment = SetAlertWhenAddingToFavFragment()
        dialogSetAlertFragment.show(childFragmentManager, null)
    }


    override fun onCancelSelected() {
        Log.d("DIALOGSELECTION", "NO")
    }

    private val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLastSalesBinding.bind(view)

        val adapter: LastSalesListAdapter = LastSalesListAdapter(callback, ::onFavoriteClicked, ::onGameClicked)
        binding.lastOffersRecyclerView.adapter = adapter
        viewModel.game.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.last_sales_options, menu)

                val buscar = menu?.findItem(R.id.search)
                val searchView = buscar?.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            getGameByTitle(query)
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun addToFavourites() {
        viewModel.addToFavourites()
    }
    fun getGameByTitle(title: String?) {
        viewModel.getGamesByTitle(title)
    }
    fun getAllSaleGames() {
        viewModel.getAllSaleGames()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
