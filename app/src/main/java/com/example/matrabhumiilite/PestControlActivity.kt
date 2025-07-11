package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class PestControlActivity : AppCompatActivity() {

    private lateinit var rvPest: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pest_control)

        rvPest = findViewById(R.id.rvPest)

        val pestList = mutableListOf<Pest>()
        val jsonStr = assets.open("pest_control.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonStr)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val title = obj.getString("title")
            val cropsAffected = obj.getString("cropsAffected")
            val controlMeasures = obj.getString("controlMeasures")
            pestList.add(Pest(title, cropsAffected, controlMeasures))
        }

        rvPest.layoutManager = LinearLayoutManager(this)
        rvPest.adapter = PestAdapter(pestList)
    }
}

