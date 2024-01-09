package com.dimovsoft.st25dv_i2c.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dimovsoft.st25dv_i2c.databinding.FragmentLoginBinding
import com.dimovsoft.st25dv_i2c.navigation.gotoMain

class LoginFragment : Fragment() {

	companion object {
		fun newInstance() = LoginFragment()
	}

	private val viewModel: LoginViewModel by viewModels()
	private var binding: FragmentLoginBinding? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentLoginBinding.inflate(inflater)
		return binding!!.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding?.apply {
			loginButton.setOnClickListener {
				loginErrorText.isVisible = false
				if (viewModel.checkCredentials(
						username.text.toString(),
						password.text.toString()
					)) {
					activity?.supportFragmentManager?.gotoMain()
				} else {
					loginErrorText.isVisible = true
				}
			}
		}

	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}
}