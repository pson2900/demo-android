package com.example.demo_structure.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demo_structure.databinding.FragmentNotificationsBinding
import com.example.demo_structure.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    val notificationsViewModel : NotificationsViewModel by viewModel()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}