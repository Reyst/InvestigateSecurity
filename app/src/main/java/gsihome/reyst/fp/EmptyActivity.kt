package gsihome.reyst.fp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class EmptyActivity : SecuredActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }

    companion object {
        fun start(parent: Activity) {
            Intent(parent, EmptyActivity::class.java)
                    .also { parent.startActivity(it) }
        }
    }
}
