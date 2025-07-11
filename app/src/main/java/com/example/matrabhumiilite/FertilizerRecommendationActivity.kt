package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.InputStream

class FertilizerRecommendationActivity : AppCompatActivity() {

    private lateinit var rvFertilizer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fertilizer_recommendation) // this must match your XML file name!

        rvFertilizer = findViewById(R.id.rvFertilizer)

        val fertilizerList = mutableListOf<Pair<String, String>>()
        val inputStream: InputStream = assets.open("fertilizer_data.json")
        val json = JSONArray(inputStream.reader().readText())

        for (i in 0 until json.length()) {
            val item = json.getJSONObject(i)
            val crop = item.getString("crop")
            val recommendation = item.getString("recommendation")
            fertilizerList.add(Pair(crop, recommendation))
        }

        rvFertilizer.layoutManager = LinearLayoutManager(this)
        rvFertilizer.adapter = FertilizerAdapter(fertilizerList)
    }
}
