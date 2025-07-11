package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class SoilHealthActivity : AppCompatActivity() {

    private lateinit var rvSoil: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soil_health)

        rvSoil = findViewById(R.id.rvSoil)

        val soilList = mutableListOf<SoilHealth>()
        val jsonStr = assets.open("soil_health.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonStr)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val title = obj.getString("title")
            val region = obj.getString("region")
            val description = obj.getString("description")
            soilList.add(SoilHealth(title, region, description))
        }

        rvSoil.layoutManager = LinearLayoutManager(this)
        rvSoil.adapter = SoilHealthAdapter(soilList)
    }
}
