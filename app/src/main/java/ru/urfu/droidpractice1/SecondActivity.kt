package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import android.os.Bundle
import android.content.Intent
import android.util.Log
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val tag = "SecondActivity"
    private val preferences by lazy { getSharedPreferences("news_state", MODE_PRIVATE) }

    companion object {
        const val READ_EXTRA = "second_article_read"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.readSwitch.isChecked = preferences.getBoolean(READ_EXTRA, false)
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            preferences.edit().putBoolean(READ_EXTRA, checked).apply()
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithResult()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        Log.d(tag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy")
        super.onDestroy()
    }

    private fun finishWithResult() {
        setResult(RESULT_OK, Intent().putExtra(READ_EXTRA, binding.readSwitch.isChecked))
        finish()
    }
}
