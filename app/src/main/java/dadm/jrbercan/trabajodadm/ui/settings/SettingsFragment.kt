package dadm.jrbercan.trabajodadm.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.preference.PreferenceFragmentCompat
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepository
import dadm.jrbercan.trabajodadm.databinding.SettingsFragmentBinding
import dadm.jrbercan.trabajodadm.ui.lastSales.SetAlertWhenAddingToFavFragment

class SettingsFragment() : PreferenceFragmentCompat() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    //private defaultPriceAlert = getDefaultPrice()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

// Then navigate to the fragment using a FragmentTransaction


   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)
        // Set the hint of the editText to the value of the variable
        binding.defaultPrice.hint = defaultPriceAlert

        binding.saveButton.setOnClickListener {
            saveDefaultPriceAlert()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    private fun getDefaultPrice() : String {
        return settingsRepository.getPrice().asLiveData().value.toString()
    }*/

}