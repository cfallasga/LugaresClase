package com.lugares_u.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lugares_u.databinding.FragmentAddLugarBinding
import com.lugares_u.databinding.LugarFilaBinding
import com.lugares_u.model.Lugar

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {
    private var listaLugares = emptyList<Lugar>()

    inner class LugarViewHolder(private val ItemBinding: LugarFilaBinding ) :
            RecyclerView.ViewHolder(ItemBinding.root){
                fun bind(lugar : Lugar){
                    ItemBinding.tbNombre.text = lugar.nombre
                    ItemBinding.etTelefonoLugar.text = lugar.telefono
                    ItemBinding.etCorreoLugarFila.text = lugar.correo
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = LugarFilaBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return  LugarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugarActual = listaLugares[position]
        holder.bind(lugarActual)
    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

    fun setData(lugares: List<Lugar>){
        this.listaLugares = lugares
        notifyDataSetChanged()//provoca que se redibuje la lista
    }


}