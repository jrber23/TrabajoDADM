package dadm.jrbercan.trabajodadm.ui.lastSales

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.jrbercan.trabajodadm.R

class SetAlertWhenAddingToFavFragment() : DialogFragment(R.layout.set_alert_dialog) {

    //Borrar cuando se implemente SharedPereferences
    companion object {
        var defaultPrice: Int = 15
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val acceptButton = view.findViewById<Button>(R.id.accept_button)
        val cancelButton = view.findViewById<Button>(R.id.cancel_button)
        val numberEditText = view.findViewById<EditText>(R.id.number_priceAlert)
        numberEditText.hint = getString(R.string.defaultPriceAlert_hint) + defaultPrice.toString()


        acceptButton.setOnClickListener {
            var alertPrice = numberEditText.text.toString().toIntOrNull()
            if (alertPrice != null) {
                Log.d("SetAlertWhenAddingToFavFragment", "Number entered: $alertPrice")
            }
            else {
                alertPrice = defaultPrice
            }
            dismiss()
            Toast.makeText(requireContext(), getString(R.string.alert_price_set_toastText)+ alertPrice, Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}