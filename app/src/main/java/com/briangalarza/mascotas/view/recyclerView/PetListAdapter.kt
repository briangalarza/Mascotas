package com.briangalarza.mascotas.view.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.util.getProgressDrawable
import com.briangalarza.mascotas.util.loadImage
import kotlinx.android.synthetic.main.item_list.view.*

class PetListAdapter (var pets:ArrayList<Pet>, var listener: ClickListener): RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {


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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PetViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false), listener)

    //Obtenemos la cantidad de elementos
    override fun getItemCount() = pets.size

    //Metodo para insertar en el recycler los distintos elementos
    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(pets[position])
    }


    //Colocamos el valor en el elemento name
    class PetViewHolder(view: View, listener: ClickListener): RecyclerView.ViewHolder(view), View.OnClickListener{

        private val imageView = view.imageView
        private val petName = view.name
        private val petCategory = view.category
        private val progressDrawable = getProgressDrawable(view.context)

        var listener:ClickListener

        init{
            this.listener = listener
            view.setOnClickListener(this)
        }

        fun bind(pet: Pet){
            //Nombre y categoria
            petName.text = pet.name
            petCategory.text = pet.category?.name

            //Compruebo que el array no se encuentre vacío
            if (pet.photos.isNotEmpty()) imageView.loadImage(pet.photos[0],progressDrawable)


        }

        override fun onClick(view: View?) {
            this.listener?.onClick(view!!,adapterPosition)

        }

    }




}