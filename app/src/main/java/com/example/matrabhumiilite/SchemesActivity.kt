package com.example.matrabhumiilite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class SchemesActivity : AppCompatActivity() {

    private lateinit var schemesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schemes)

        schemesTextView = findViewById(R.id.schemesTextView)

        // Load schemes.json from assets
        val schemesText = loadJSONFromAsset("govt_schemes.json")
        schemesTextView.text = schemesText
    }

    private fun loadJSONFromAsset(filename: String): String {
        return try {
            val inputStream = assets.open(filename)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }

            stringBuilder.toString()
        } catch (ex: Exception) {
            "Schemes data not available"
        }
    }
}

