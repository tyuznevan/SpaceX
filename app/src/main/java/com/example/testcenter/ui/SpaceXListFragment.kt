package com.example.testcenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.testcenter.R
import com.example.testcenter.SpaceX.SpaceXApp
//import com.example.testcenter.SpaceX.SpaceXApp
import com.example.testcenter.SpaceX.data.remote.space.SpaceXApi
import com.example.testcenter.databinding.SpaceXListFragmentBinding

class SpaceXListFragment : Fragment(R.layout.space_x_list_fragment) {


    private var _binding: SpaceXListFragmentBinding? = null

    private val binding get() = _binding!!

    private val adapter = MissionsListAdapter()
    private var myList: RecyclerView? = null


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
        spaceXListViewModel.fetchSpaceXList()



        val recyclerView = binding.RecView

        recyclerView.adapter = adapter

        spaceXListViewModel.spaceXLiveData.observe(viewLifecycleOwner, {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        })

//        adapter.notifyDataSetChanged()
//
//
//
//        binding.RecView.setOnClickListener {
//            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}