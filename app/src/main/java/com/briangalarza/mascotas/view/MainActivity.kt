package com.briangalarza.mascotas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.view.recyclerView.ClickListener
import com.briangalarza.mascotas.view.recyclerView.PetListAdapter
import com.briangalarza.mascotas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Esta variable se instanciara al momento del onCreate
    lateinit var viewModel: ListViewModel
    //Pasamos un array vacío al adapter
    private var petsAdapter: PetListAdapter? = null


    companion object {
        const val ID = "ID"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creamos el adapter con el clickListener
        petsAdapter = PetListAdapter(arrayListOf(), object : ClickListener {
            override fun onClick(view: View, index: Int) {

                //Toast.makeText(applicationContext,petsAdapter?.pets?.get(index)?.id?.toString(), Toast.LENGTH_SHORT).show()

                viewModel.fetchByID(petsAdapter?.pets?.get(index)?.id)
                //observeViewModel()

                //Toast.makeText(applicationContext,viewModel.pet.value?.name,Toast.LENGTH_SHORT).show()

                //Pasamos a la pantalla de details

                /*
                val intent = Intent(applicationContext, Pet::class.java)
                intent.putExtra(ID,petsAdapter?.pets?.get(index)?.id)
                startActivity(intent)
                */
            }
        })


        //viewModel instanciado
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        //Llammamos al metodo encapsulado
        viewModel.refresh()

        //Declaramos el adapter al recycler view
        petList.apply {
            layoutManager = LinearLayoutManager(context)

            adapter = petsAdapter
        }


        observeViewModel()


    }

    /**
     * LLama a las variables del view Model, observer
     */
    fun observeViewModel() {

        //Observables

        //actualiza la información de las mascotas
        viewModel.pets.observe(this, Observer { pets ->
            //Si pets no es vacio hacemos que actualize el adapter de countries con el valor que le corresponde a pets
            pets?.let {
                petList.visibility = View.VISIBLE
                petsAdapter?.updatePets(it)
            }
        })

        //carga el mensaje de error en caso de generarse
        viewModel.petLoadError.observe(this, Observer { isError ->
            isError?.let {
                error_message.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    petList.visibility = View.GONE
                }
            }
        })

        //muestra la animacion de carga
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    error_message.visibility = View.GONE
                    petList.visibility = View.GONE
                }
            }
        })
        viewModel.pet.observe(this, Observer { pet ->
            pet?.let {
                petList.visibility = View.VISIBLE
                val intent = Intent(applicationContext, Pet::class.java)

                //Mandamos el objeto
                intent.putExtra("Bundle", pet)
                startActivity(intent)
            }
        })

    }


}
