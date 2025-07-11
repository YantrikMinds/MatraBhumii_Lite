package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class OrganicFarmingActivity : AppCompatActivity() {

    private lateinit var rvTips: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organic_farming)

        rvTips = findViewById(R.id.rvTips)

        val tips = mutableListOf<String>()
        val jsonStr = assets.open("organic_tips.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonStr)

        for (i in 0 until jsonArray.length()) {
            tips.add(jsonArray.getString(i))
        }

        rvTips.layoutManager = LinearLayoutManager(this)
        rvTips.adapter = OrganicFarmingAdapter(tips)
    }
}
