package com.briangalarza.mascotas.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.model.PetsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel:ViewModel() {

    //Servicio de retrofit
    private val petsService = PetsService()
    //RxJava
    private val disposable = CompositeDisposable()

    //Generamos la variable de tipo LiveData para que los suscriptores reciban las actualizaciones
    val pets = MutableLiveData<List<Pet>>()
    //Variable pet
    val pet = MutableLiveData<Pet>()

    //Variable de error
    val petLoadError = MutableLiveData<Boolean>()
    //Variable de carga
    val loading = MutableLiveData<Boolean>()


    //Metodo que invoca a la busqueda, ocultando la funcionalidad
    fun refresh(){
        fetchPets()
    }

    //Metodo que invoca a la busqueda por Id
    fun fetchByID(id: Long?){
       fetchPetByID(id)
    }


    //Metodo que hace la busqueda de paises
    private fun fetchPets(){
        //Cargamos true
        loading.value = true

        //Usamos el disposable para subcribirnos al servicio para obtener la información
        disposable.add(
            petsService.getPets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Pet>>(){

                    // En caso de que funcione
                    override fun onSuccess(value: List<Pet>?) {
                        pets.value = value
                        petLoadError.value = false
                        loading.value = false
                    }

                    // En caso de error
                    override fun onError(e: Throwable?) {
                        petLoadError.value = true
                        loading.value = false
                    }

                })
        )

    }

    private fun fetchPetByID(id: Long?){
        //Cargamos true
        loading.value = true

        //Usamos el disposable para subcribirnos al servicio para obtener la información
        disposable.add(
            petsService.getPetByID(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Pet>(){

                    // En caso de que funcione
                    override fun onSuccess(value: Pet?) {
                        pet.value = value
                        petLoadError.value = false
                        loading.value = false
                    }

                    // En caso de error
                    override fun onError(e: Throwable?) {
                        petLoadError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}