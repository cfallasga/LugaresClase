package com.lugares_u.ui.lugar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.lugares_u.R
import com.lugares_u.databinding.FragmentAddLugarBinding
import com.lugares_u.model.Lugar
import com.lugares_u.viewmodel.LugarViewModel

class Add_Lugar_Fragment : Fragment() {
    private var _binding: FragmentAddLugarBinding? = null

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

        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)
        binding.btAddLugar.setOnClickListener {
            addLugar()
        }
        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addLugar(){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isNotEmpty()){
            val lugar = Lugar(0,nombre,correo,telefono,0.0,0.0,0.0,"","")
            lugarViewModel.addLugar(lugar)
            Toast.makeText(requireContext(),"",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_nav_lugar_to_add_Lugar_Fragment)
        }else{
            Toast.makeText(requireContext(),"Falan datos!!",Toast.LENGTH_LONG).show()
        }
    }
}
