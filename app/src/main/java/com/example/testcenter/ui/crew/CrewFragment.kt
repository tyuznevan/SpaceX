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
import com.example.testcenter.utils.Utils

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

    private fun initView() {
        binding.largeLogo.load(arguments?.getString(SpaceXListViewModel.LOGO_KEY))
        binding.nameCrew.text = arguments?.getString(SpaceXListViewModel.NAME_KEY)
        binding.flightCrew.text = (arguments?.getInt(SpaceXListViewModel.FLIGHT_KEY) ?: 0).toString()
        binding.succesCrew.text = Utils.makeSuccessLabel(arguments?.getBoolean(SpaceXListViewModel.SUCCESS_KEY))
        binding.dateCrew.text = arguments?.getString(SpaceXListViewModel.DATE_KEY)
        binding.details.text = arguments?.getString(SpaceXListViewModel.DETAILS_KEY)
        binding.crewNames.text = arguments?.getString(SpaceXListViewModel.CREWNAME_KEY)
        binding.crewStatus.text = arguments?.getString(SpaceXListViewModel.CREWSTATUS_KEY)
        binding.crewAgency.text = arguments?.getString(SpaceXListViewModel.CREWAGENCY_KEY)

    }

}