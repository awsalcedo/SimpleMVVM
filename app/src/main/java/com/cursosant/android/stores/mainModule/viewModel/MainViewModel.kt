package com.cursosant.android.stores.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.mainModule.model.MainInteractor

class MainViewModel : ViewModel() {

    //Interactor
    private var interactor: MainInteractor

    //Properties
    //private var stores: MutableLiveData<List<StoreEntity>>

    init {
        interactor = MainInteractor()
    }

    //Initialize variable using lazy and do something else with the scope function called also
    private val stores : MutableLiveData<List<StoreEntity>> by lazy {
        MutableLiveData<List<StoreEntity>>().also {
            loadStores()
        }
    }

    //Propertie to be observed within the UI
    fun getStores(): LiveData<List<StoreEntity>> {
        return stores
    }

    private fun loadStores() {
        interactor.getStoresCallback(object : MainInteractor.StoresCallback {
            override fun getStoresCallback(stores: MutableList<StoreEntity>) {
                this@MainViewModel.stores.value = stores
            }
        })

    }
}