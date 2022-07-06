package com.example.testcenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcenter.R
import com.example.testcenter.databinding.SpaceXListFragmentBinding

class SpaceXListFragment : Fragment(R.layout.space_x_list_fragment) {


    private var _binding: SpaceXListFragmentBinding? = null

    private val binding get() = _binding!!

    private val spaceXListViewModel: SpaceXListViewModel by viewModels()


    private val adapter = MissionsListAdapter { onClick(it, spaceXListViewModel) }

    private fun onClick(position: Int, iewModel: SpaceXListViewModel) {

        spaceXListViewModel.spaceXLiveData.observe(viewLifecycleOwner) {



            findNavController().navigate(
                R.id.action_spaceXListFragment_to_crewFragment,
                bundleOf("Logo" to it[position].links?.patch?.large),
                //bundleOf("Name" to it[position].name)
            )

        }

        //Toast.makeText(activity, "тРОЛЛИНГ", Toast.LENGTH_SHORT).show()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SpaceXListFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spaceXListViewModel.fetchSpaceXList(requireContext())
        spaceXListViewModel.fetchCrewList(requireContext())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val spaceXListViewModel: SpaceXListViewModel by viewModels()
//        spaceXListViewModel.fetchSpaceXList(requireContext())
//        spaceXListViewModel.fetchCrewList(requireContext())

        val recyclerView = binding.RecView

        spaceXListViewModel.crewLiveData.observe(viewLifecycleOwner) {
            val k = it[0].name

        }

        recyclerView.adapter = adapter

        spaceXListViewModel.spaceXLiveData.observe(viewLifecycleOwner) {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}