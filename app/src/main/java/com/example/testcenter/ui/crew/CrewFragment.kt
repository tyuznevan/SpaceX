package com.example.testcenter.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import coil.load
import com.example.testcenter.R
import com.example.testcenter.databinding.CrewFragmentBinding
import com.example.testcenter.ui.spaceXList.SpaceXListViewModel

class CrewFragment : Fragment(R.layout.crew_fragment) {

    private var _binding: CrewFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CrewFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initView() {
        //TODO:
        val nameText = binding.nameCrew
        val flight = binding.flightCrew
        val success = binding.succesCrew
        val date = binding.dateCrew
        val details = binding.details

        //TODO:
        binding.largeLogo.load(arguments?.getString(SpaceXListViewModel.LOGO_KEY))
        nameText.text = arguments?.getString("Name")
        flight.text = arguments?.getString("Flight")
        success.text = arguments?.getString("Success")
        date.text = arguments?.getString("Date")
        details.text = arguments?.getString("Details")
    }

}