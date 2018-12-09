package gsihome.reyst.fp

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

abstract class SecuredActivity : AppCompatActivity() {

    override fun onResume() {
        val pinNeeded = App.isPinNeeded
        if (pinNeeded) {
            requestPin()
        }
        super.onResume()

    }

    @SuppressLint("InflateParams")
    private fun requestPin() {

        val view = layoutInflater.inflate(R.layout.enter_pin, null)
        val etPin: EditText = view.findViewById(R.id.pin)

        AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.enter_pin)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    if (etPin.text?.toString().hashCode() != readSavedPin()) {
                        dialog.dismiss()
                        requestPin()
                    }
                }
                .show()
    }

    private fun readSavedPin(): Int? = getSharedPreferences(App.PIN, Context.MODE_PRIVATE).getInt(App.PIN, 0)

}