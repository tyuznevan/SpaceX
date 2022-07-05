package com.example.testcenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcenter.R
import com.example.testcenter.SpaceX.SpaceXApp
import com.example.testcenter.databinding.SpaceXListFragmentBinding
import java.security.AccessController.getContext

class SpaceXListFragment : Fragment(R.layout.space_x_list_fragment) {


    private var _binding: SpaceXListFragmentBinding? = null

    private val binding get() = _binding!!

    private val adapter = MissionsListAdapter { onClick(it) }

    private fun onClick(position: Int) {
        // создать бандл val argument = bundleOf("prekl" to "shok")
        adapter.data[position].id
        Toast.makeText(activity, "тРОЛЛИНГ", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_spaceXListFragment_to_crewFragment)

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spaceXListViewModel: SpaceXListViewModel by viewModels()
        spaceXListViewModel.fetchSpaceXList(requireContext())



        val recyclerView = binding.RecView

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