package com.dimovsoft.st25dv_i2c.ui

import androidx.lifecycle.ViewModel
import com.dimovsoft.st25dv_i2c.data.DatabaseRepository
import org.koin.java.KoinJavaComponent.inject


class LoginViewModel : ViewModel() {
	private val repository: DatabaseRepository by inject(DatabaseRepository::class.java)

	fun checkCredentials(username: String, password: String): Boolean {
		return repository.checkCredentials(username, password)
	}
}