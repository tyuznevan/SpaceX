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

    private val adapter = MissionsListAdapter { onClick(it) }

    private fun onClick(position: Int) {
        viewModel.fetchCrewList(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchSpaceXList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SpaceXListFragmentBinding.inflate(inflater, container, false)
        val recyclerView = binding.RecView
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.spaceXLiveData.observe(viewLifecycleOwner) {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToCrewScreen.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_spaceXListFragment_to_crewFragment,
                it
            )
        }
    }

}