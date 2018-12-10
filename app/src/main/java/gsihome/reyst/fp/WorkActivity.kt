package gsihome.reyst.fp

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import gsihome.reyst.fp.databinding.ActivityWorkBinding

class WorkActivity : SecuredActivity() {

    private lateinit var binding: ActivityWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work)

        binding.btnNext1.setOnClickListener {
            EmptyActivity.start(this)
        }

        binding.btnNext2.setOnClickListener {
            BasicActivity.start(this)
        }

    }

    companion object {
        fun start(parent: Activity) {
            Intent(parent, WorkActivity::class.java)
                    .also { parent.startActivity(it) }
        }
    }
}
