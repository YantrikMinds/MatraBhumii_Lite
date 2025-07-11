package com.example.matrabhumiilite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.InputStream

class CropCalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_calendar)

        val textView = findViewById<TextView>(R.id.cropCalendarTextView)

        val json = loadJSONFromAsset("crop_calendar.json")
        val formattedText = parseCropCalendar(json)
        textView.text = formattedText
    }

    private fun loadJSONFromAsset(filename: String): String {
        val inputStream: InputStream = assets.open(filename)
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun parseCropCalendar(json: String): String {
        val result = StringBuilder()
        val jsonObject = JSONObject(json)

        for (region in jsonObject.keys()) {
            result.append("📍 $region:\n")
            val months = jsonObject.getJSONObject(region)
            for (month in months.keys()) {
                val crops = months.getJSONArray(month)
                val cropList = mutableListOf<String>()
                for (i in 0 until crops.length()) {
                    cropList.add(crops.getString(i))
                }
                result.append("  • $month: ${cropList.joinToString(", ")}\n")
            }
            result.append("\n")
        }

        return result.toString()
    }
}
