package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader



class MarketPriceActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MarketPriceAdapter
    private lateinit var priceList: List<MarketPrice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_price)

        recyclerView = findViewById(R.id.recyclerMarket)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load JSON
        val inputStream = assets.open("market_prices.json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.readText()
        val type = object : TypeToken<List<MarketPrice>>() {}.type
        priceList = Gson().fromJson(jsonString, type)

        adapter = MarketPriceAdapter(priceList)
        recyclerView.adapter = adapter
    }
}
