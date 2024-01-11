package com.dimovsoft.st25dv_i2c.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.dimovsoft.st25dv_i2c.databinding.FragmentLoginBinding
import com.dimovsoft.st25dv_i2c.navigation.gotoMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

	companion object {
		fun newInstance() = LoginFragment()
	}

	private val viewModel: LoginViewModel by viewModel()
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
			viewModel.username.observe(viewLifecycleOwner) {
				if (username.text.toString() != it) {
					username.setText(it)
				}
			}
			username.addTextChangedListener { viewModel.setUsername(it.toString()) }

			viewModel.password.observe(viewLifecycleOwner) {
				if (password.text.toString() != it) {
					password.setText(it)
				}
			}
			password.doOnTextChanged { text, _, _, _ -> viewModel.setPassword(text.toString()) }

			loginButton.setOnClickListener {
				loginErrorText.isVisible = false
				if (viewModel.checkCredentials()) {
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