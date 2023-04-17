package dadm.jrbercan.trabajodadm.ui.lastSales

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.jrbercan.trabajodadm.R

class AddToFavouritesDialogFragment() : DialogFragment(R.layout.fragment_last_sales) {

    private val viewModel: LastSalesViewModel by activityViewModels()

    var callback: AddToFavouritesCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_favorite_dialog_title)
            .setMessage(R.string.add_favorite_dialog_message)
            .setPositiveButton(R.string.dialog_yes) { _, _ ->
                Log.d("ADD", "GAME ADDED")
                callback?.onFavouriteSelected()
                dismiss()
            }
            .setNegativeButton(R.string.dialog_no) { _, _ ->
                callback?.onCancelSelected()
                dismiss()
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddToFavouritesCallback) {
            callback = context
        } else {
            //throw RuntimeException("$context must implement AddToFavouritesCallback")
        }
    }

    interface AddToFavouritesCallback {
        fun onFavouriteSelected()
        fun onCancelSelected()
    }
}
