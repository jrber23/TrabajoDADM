package dadm.jrbercan.trabajodadm.ui.lastSales

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.FragmentLastSalesBinding
import dadm.jrbercan.trabajodadm.ui.favouritesGames.DeleteAllGamesDialogFragment
import dadm.jrbercan.trabajodadm.ui.favouritesGames.FavouriteGamesListAdapter


class LastSalesFragment : Fragment(R.layout.fragment_last_sales) {
    private var _binding: FragmentLastSalesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LastSalesViewModel by activityViewModels()
    private val callback = object : LastSalesListAdapter.ItemClicked {
        override fun onClick(author: String) {
            AddToFavouritesDialogFragment().show(childFragmentManager, null)
        }

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

        val adapter: LastSalesListAdapter = LastSalesListAdapter(callback)
        binding.lastOffersRecyclerView.adapter = adapter
        viewModel.game.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider, SearchView.OnQueryTextListener {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.last_sales_options, menu)
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

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
