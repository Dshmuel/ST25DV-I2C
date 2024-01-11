package com.dimovsoft.st25dv_i2c.ui

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dimovsoft.st25dv_i2c.R
import com.dimovsoft.st25dv_i2c.navigation.gotoLogin
import com.dimovsoft.st25dv_i2c.nfc.NfcManager
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {
	private val nfcManager: NfcManager by inject(NfcManager::class.java)
	private lateinit var nfcAdapter: NfcAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		if (savedInstanceState == null) {
			supportFragmentManager.gotoLogin()
		}
		nfcAdapter = NfcAdapter.getDefaultAdapter(this)
		nfcManager.mockReceivingSamples()
	}

	override fun onResume() {
		super.onResume()

		if (!nfcAdapter.isEnabled) {
			startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
		}

		nfcAdapter.enableReaderMode(this,
			{ foundTag -> nfcManager.tagDiscovered(foundTag) },
			NfcAdapter.FLAG_READER_NFC_V, Bundle())
	}

	override fun onPause() {
		super.onPause()
		nfcAdapter.disableReaderMode(this)
	}
}