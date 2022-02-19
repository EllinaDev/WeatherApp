package com.example.weatherapp.ui.fiveday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentFiveDayBinding

class FiveDayFragment : Fragment() {
    private var _binding: FragmentFiveDayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFiveDayBinding.inflate(inflater)
        return binding.root
    }

    companion object{
        fun newInstance() = FiveDayFragment()

    }
}
