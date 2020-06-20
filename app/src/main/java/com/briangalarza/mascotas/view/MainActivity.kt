package com.briangalarza.mascotas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Esta variable se instanciara al momento del onCreate
    lateinit var viewModel: ListViewModel
    //Pasamos un array vacío al adapter
    private val petsAdapter = PetListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
    fun observeViewModel(){

        //Observables

        //Observer que actualiza la información de las mascotas
        viewModel.pets.observe(this, Observer { pets ->
            //Si pets no es vacio hacemos que actualize el adapter de countries con el valor que le corresponde a pets
            pets?.let{
                petList.visibility = View.VISIBLE
                petsAdapter.updatePets(it) }
        })

        //Observer que carga el mensaje de error en caso de generarse
        viewModel.petLoadError.observe(this, Observer { isError ->
            isError?.let{error_message.visibility = if(it) View.VISIBLE else View.GONE
                if (it){
                    petList.visibility = View.GONE
                }
            }})

        //Observer que carga muestra la animacion de carga
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    error_message.visibility = View.GONE
                    petList.visibility = View.GONE
                }
            }
        })

    }

}
