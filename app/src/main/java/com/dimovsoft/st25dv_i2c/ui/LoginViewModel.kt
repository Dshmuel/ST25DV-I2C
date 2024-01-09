package com.dimovsoft.st25dv_i2c.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimovsoft.st25dv_i2c.data.DatabaseRepository
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel : ViewModel() {
	private val repository: DatabaseRepository by inject(DatabaseRepository::class.java)

	private var _username = MutableLiveData("")
	val username: LiveData<String> = _username

	private var _password = MutableLiveData("")
	val password: LiveData<String> = _password

	fun checkCredentials(): Boolean {
		return repository.checkCredentials(username.value, password.value)
	}

	fun setUsername(username: String) {
		_username.value = username
	}

	fun setPassword(password: String) {
		_password.value = password
	}
}