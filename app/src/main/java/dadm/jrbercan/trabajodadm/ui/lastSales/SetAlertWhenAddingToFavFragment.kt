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
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepository
import dadm.jrbercan.trabajodadm.domain.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class SetAlertWhenAddingToFavFragment(private val defaultPrice : String, private val game : Game) : DialogFragment(R.layout.set_alert_dialog) {

    var callback: SetAlarmCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val acceptButton = view.findViewById<Button>(R.id.accept_button)
        val cancelButton = view.findViewById<Button>(R.id.cancel_button)
        val numberEditText = view.findViewById<EditText>(R.id.number_priceAlert)
        numberEditText.hint = getString(R.string.defaultPriceAlert_hint) + defaultPrice


        acceptButton.setOnClickListener {
            var alertPrice = numberEditText.text.toString()
            if (alertPrice != null) {
                Log.d("SetAlertWhenAddingToFavFragment", "Number entered: $alertPrice")

            }
            else {
                alertPrice = defaultPrice
            }
            callback?.onSetAlert(game.id, alertPrice)
            dismiss()
            Toast.makeText(requireContext(), getString(R.string.alert_price_set_toastText)+ alertPrice, Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SetAlarmCallback) {
            callback = context
        } else {

        }
    }

    interface SetAlarmCallback {
        fun onSetAlert(gameId : String, price : String)
    }
}