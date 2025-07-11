package com.example.matrabhumiilite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LanguageSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)

        val btnEnglish = findViewById<Button>(R.id.btnEnglish)


        btnEnglish.setOnClickListener {
            goToMainActivity("en")
        }

    }

    private fun goToMainActivity(languageCode: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("LANGUAGE_CODE", languageCode)
        startActivity(intent)
        finish()
    }
}
