package com.example.testcenter.ui.spaceXList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcenter.R
import com.example.testcenter.databinding.SpaceXListFragmentBinding
import com.example.testcenter.ui.missionsAdapter.MissionsListAdapter

class SpaceXListFragment : Fragment(R.layout.space_x_list_fragment) {


    private var _binding: SpaceXListFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SpaceXListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!viewModel.rotationState) {
            viewModel.fetchSpaceXList()
        }
        viewModel.rotationState = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SpaceXListFragmentBinding.inflate(inflater, container, false)
        val recyclerView = binding.RecView
        recyclerView.adapter = viewModel.adapter
        initObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.spaceXLiveData.observe(viewLifecycleOwner) {
            viewModel.adapter.data.addAll(it)
            viewModel.adapter.notifyDataSetChanged()
        }

        viewModel.navigateToCrewScreen.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_spaceXListFragment_to_crewFragment,
                it
            )
        }
    }

}