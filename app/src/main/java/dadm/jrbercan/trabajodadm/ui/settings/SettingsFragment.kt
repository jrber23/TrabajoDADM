package dadm.jrbercan.trabajodadm.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.SettingsFragmentBinding
import dadm.jrbercan.trabajodadm.ui.lastSales.SetAlertWhenAddingToFavFragment

class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private var defaultPriceAlert: Int = 15



// Then navigate to the fragment using a FragmentTransaction


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)
        // Set the hint of the editText to the value of the variable
        binding.defaultPrice.hint = defaultPriceAlert.toString()

        binding.saveButton.setOnClickListener {
            saveDefaultPriceAlert()
        }

        binding.defaultPrice.hint = defaultPriceAlert.toString()

        // Borrar cuando se implemente SharedPreferences
        SetAlertWhenAddingToFavFragment.defaultPrice = defaultPriceAlert

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun saveDefaultPriceAlert() {
        val inputText = binding.defaultPrice.text.toString()
        if (inputText.isNotEmpty()) {
            defaultPriceAlert = inputText.toInt()
            binding.defaultPrice.hint = defaultPriceAlert.toString()

            SetAlertWhenAddingToFavFragment.defaultPrice = defaultPriceAlert
        }
    }

}