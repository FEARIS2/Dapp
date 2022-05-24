package com.example.dapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.dapp.R
import com.example.dapp.databinding.FragmentHomeBinding

private lateinit var binding : FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.buytap.setOnClickListener{
            it.findNavController().navigate(R.id.ticketBuyFragment)
        }

        binding.listtap.setOnClickListener{
            it.findNavController().navigate(R.id.ticketListFragment)
        }

        return binding.root
    }
}