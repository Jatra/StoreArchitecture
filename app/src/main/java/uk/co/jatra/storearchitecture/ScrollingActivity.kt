package uk.co.jatra.storearchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import uk.co.jatra.storearchitecture.ui.UserViewModel

private val TAG = ScrollingActivity::class.java.simpleName

class ScrollingActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels(
        //see https://stackoverflow.com/a/56908621/2512177
        //https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate
        //SavedStateViewModelFactory not actually used in the ViewModel at present
        factoryProducer = { SavedStateViewModelFactory(application, this) }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            setLoading()
            viewModel.refresh()
        }

        viewModel.data.observe(this, Observer {
            val sb: StringBuilder = StringBuilder()
            for (name in it) {
                Log.d(TAG, "new name: $name")
                sb.append("$name\n")
            }
            contentText.text = sb.toString()
        })
    }

    fun setLoading() {
        contentText.text = "Loading..."
    }
}
