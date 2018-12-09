package gsihome.reyst.fp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import gsihome.reyst.fp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: PinViewModel by lazy {
        ViewModelProviders.of(this).get(PinViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.pin1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updatePin(s?.toString() ?: "")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.pin2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.confirmPin(s?.toString() ?: "")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.button.setOnClickListener {
            saveHashOfPin()
            WorkActivity.start(this@MainActivity)
            this@MainActivity.finish()
        }

        viewModel.getObservableIsPinCorrect().observe(this, Observer {
            binding.button.isEnabled = it == true
        })

    }

    private fun saveHashOfPin() {
        val prefs = getSharedPreferences(App.PIN, Context.MODE_PRIVATE)
        prefs.edit()
                .putInt(App.PIN, viewModel.getObservablePin().value.hashCode())
                .apply()
    }
}
