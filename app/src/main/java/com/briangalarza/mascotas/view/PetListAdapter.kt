package com.briangalarza.mascotas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.util.getProgressDrawable
import kotlinx.android.synthetic.main.item_list.view.*

class PetListAdapter (var pets:ArrayList<Pet>): RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {


    /**
     * Metodo para actualizar el listado de elementos en el recycler
     *
     */
    fun updatePets(newPets:List<Pet>){
        pets.clear()
        pets.addAll(newPets)
        notifyDataSetChanged()
    }

    //Mandamos nuestro formato de elementos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PetViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))

    //Obtenemos la cantidad de elementos
    override fun getItemCount() = pets.size

    //Metodo para insertar en el recycler los distintos elementos
    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(pets[position])

    }


    //Colocamos el valor en el elemento name
    class PetViewHolder(view: View): RecyclerView.ViewHolder(view){

        //private val imageView = view.imageView
        private val petName = view.name
        private val petStatus = view.status
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(pet: Pet){
            petName.text = pet.name
            petStatus.text = pet.status
            //imageView.loadImage(country.flag,progressDrawable)
        }



    }


}