package com.example.dapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.dapp.R
import com.example.dapp.databinding.FragmentTicketBuyBinding

private lateinit var binding : FragmentTicketBuyBinding

class TicketBuyFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ticket_buy, container, false)

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.homeFragment)
        }

        binding.listtap.setOnClickListener {
            it.findNavController().navigate(R.id.ticketListFragment)
        }
        return binding.root
    }
}
