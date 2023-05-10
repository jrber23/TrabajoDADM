package dadm.jrbercan.trabajodadm.ui.favouritesGames

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.domain.model.Game

class DeleteAllGamesDialogFragment(private val games : List<Game>): DialogFragment(R.layout.fragment_favourites_games) {

    private val viewModel: FavouritesGamesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_all_games_dialog_title)
            .setMessage(R.string.delete_all_games_dialog_message)
            .setPositiveButton(R.string.dialog_yes) {_,_ ->
                Log.d("DELETE", "ALL DELETED")
                viewModel.deleteAllFavouriteGames(games)
            }
            .setNegativeButton(R.string.dialog_no) { _,_ ->
                dismiss()
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


}