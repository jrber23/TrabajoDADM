package dadm.jrbercan.trabajodadm.data.settings

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}