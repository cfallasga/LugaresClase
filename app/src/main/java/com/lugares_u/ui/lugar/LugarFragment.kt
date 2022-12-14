package com.lugares_u.ui.lugar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lugares_u.R
import com.lugares_u.adapter.LugarAdapter
import com.lugares_u.databinding.FragmentLugarBinding
import com.lugares_u.viewmodel.LugarViewModel

class LugarFragment : Fragment() {

    private var _binding: FragmentLugarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel =
            ViewModelProvider(this).get(LugarViewModel::class.java)

        _binding = FragmentLugarBinding.inflate(inflater, container, false)
        binding.btAddLugarFab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_lugar_to_add_Lugar_Fragment)
        }
        //activar el recyclerView

        val lugarAdapter = LugarAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = lugarAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        lugarViewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        lugarViewModel.getAllData.observe(viewLifecycleOwner){
            lugares ->
            lugarAdapter.setData(lugares)
        }

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}