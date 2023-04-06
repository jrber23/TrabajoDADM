package dadm.jrbercan.trabajodadm.data.lastSales

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.FragmentLastSalesBinding
import dadm.jrbercan.trabajodadm.databinding.SettingsFragmentBinding

class LastSalesFragment : Fragment(R.layout.fragment_last_sales) {
    private var _binding: FragmentLastSalesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLastSalesBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}