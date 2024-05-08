package com.example.chantoast

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val maxCount = intent.getIntExtra("count", 0)
        val randomCount = Random.nextInt(maxCount + 1)
        val explain: TextView = findViewById(R.id.explain)
        val realRandomCount: TextView = findViewById(R.id.random_count)

        explain.text = "Here is a random\nnumber between 0\nand $maxCount"

        realRandomCount.text = "$randomCount"

        val returnIntent = Intent()
        returnIntent.putExtra("randomCount", randomCount)
        setResult(RESULT_OK, returnIntent)

        realRandomCount.setOnClickListener {
            finish()
        }
    }
}