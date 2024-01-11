package com.dimovsoft.st25dv_i2c.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimovsoft.st25dv_i2c.data.DatabaseRepository
import com.dimovsoft.st25dv_i2c.models.RealmDataSample
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class MainViewModel : ViewModel() {
	private val repository: DatabaseRepository by KoinJavaComponent.inject(DatabaseRepository::class.java)

	private val _samplesList = MutableLiveData<List<RealmDataSample>>()
	val samplesList: LiveData<List<RealmDataSample>> = _samplesList

	fun updateSamples() {
		viewModelScope.launch {
			repository.getSamples().collect {
				_samplesList.value = it
			}
		}
	}
}