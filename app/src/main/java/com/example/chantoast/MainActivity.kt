package com.example.chantoast

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainCount: TextView = findViewById(R.id.main_count)
        val buttonDialog: Button = findViewById(R.id.button_dialog)
        val buttonCount: Button = findViewById(R.id.button_count)
        val buttonRandom: Button = findViewById(R.id.button_random)

        mainCount.text = count.toString()

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
            val intent = Intent(this, SecondFragment::class.java).apply {
                putExtra("count", count)
            }
            startForResult.launch(intent)
        }


        buttonDialog.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("AlertDialog 입니다")
        builder.setMessage("버튼을 클릭해주세요")

        builder.setPositiveButton("Count 초기화") { dialog, _ ->
            count = 0
            dialog.dismiss()
        }

        builder.setNeutralButton("TOAST") { dialog, _ ->
            Toast.makeText(this, "TOAST 메시지", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("닫기") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}