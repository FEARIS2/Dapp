package com.example.dapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.dapp.R
import com.example.dapp.databinding.FragmentTicketListBinding
import java.io.*
import java.net.InetAddress
import java.net.Socket

private lateinit var binding : FragmentTicketListBinding
class TicketListFragment : Fragment() {
    private fun connectServer(IPaddr: String?, port: Int, command: String?): String? {
        var socket: Socket? = null
        var reader: BufferedReader? = null
        var writer: PrintWriter? = null
        try {
            val addr = InetAddress.getByName(IPaddr)
            socket = Socket(addr, port)
            reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            writer = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            writer!!.println(command)
            writer.flush()
            var str: String? = null
            while (reader!!.readLine().also { str = it } != null) {
                return str;
                //println(str)
            }
            socket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ticket_list, container, false)

        binding.viewticket.setOnClickListener {
            var result: String? = null
            Thread {
                result = connectServer("chsui.iptime.org", 20001, "query getAllTickets")
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed(Runnable {
                    Toast.makeText(activity, result.toString(), Toast.LENGTH_LONG).show() // 프래그먼트는 인자로 this가 아닌 getActivity를 넘겨줘야 함
                }, 0)
            }.start()
        }

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.homeFragment)
        }

        binding.buytap.setOnClickListener{
            it.findNavController().navigate(R.id.ticketBuyFragment)
        }
        return binding.root
    }
}