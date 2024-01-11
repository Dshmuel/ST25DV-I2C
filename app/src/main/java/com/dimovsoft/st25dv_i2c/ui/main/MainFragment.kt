package com.dimovsoft.st25dv_i2c.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimovsoft.st25dv_i2c.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

	companion object {
		fun newInstance() = MainFragment()
	}

	private val viewModel: MainViewModel by viewModel()
	private var binding: FragmentMainBinding? = null
	private var adapter: MyListAdapter = MyListAdapter()
	private var layoutManager = LinearLayoutManager(context)

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentMainBinding.inflate(inflater)
		return binding!!.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.updateSamples()
		binding?.apply {
			recyclerView.layoutManager = layoutManager
			recyclerView.adapter = adapter
			viewModel.samplesList.observe(viewLifecycleOwner) {
				adapter.submitList(it)
				recyclerView.postDelayed(200) {
					layoutManager.smoothScrollToPosition(recyclerView, null, 0)
				}
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}
}