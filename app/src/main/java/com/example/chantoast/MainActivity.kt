package com.example.chantoast

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainCount: TextView = findViewById(R.id.main_count)
        val buttonToast: Button = findViewById(R.id.button_toast)
        val buttonCount: Button = findViewById(R.id.button_count)
        val buttonRandom: Button = findViewById(R.id.button_random)

        mainCount.text = count.toString()

        buttonToast.setOnClickListener {
            Toast.makeText(this, "TOAST 메시지", Toast.LENGTH_SHORT).show()
        }

        buttonCount.setOnClickListener {
            count++
            mainCount.text = count.toString()
        }

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getIntExtra("randomCount", 0) ?: 0
                count = data
                mainCount.text = count.toString()
            }
        }

        buttonRandom.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("count", count)
            }
            startForResult.launch(intent)
        }
    }
}