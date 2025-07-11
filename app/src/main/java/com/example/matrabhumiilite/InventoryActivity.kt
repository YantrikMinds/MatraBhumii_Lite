package com.example.matrabhumiilite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventoryActivity : AppCompatActivity() {

    private lateinit var db: InventoryDatabaseHelper
    private lateinit var rvInventory: RecyclerView
    private lateinit var adapter: InventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        db = InventoryDatabaseHelper(this)

        val etItemName = findViewById<EditText>(R.id.etItemName)
        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        rvInventory = findViewById(R.id.rvInventory)

        rvInventory.layoutManager = LinearLayoutManager(this)
        refreshList()

        btnAdd.setOnClickListener {
            val name = etItemName.text.toString()
            val quantity = etQuantity.text.toString()
            if (name.isNotEmpty() && quantity.isNotEmpty()) {
                db.insertItem(name, quantity)
                etItemName.text.clear()
                etQuantity.text.clear()
                refreshList()
            }
        }
    }

    private fun refreshList() {
        val items = db.getAllItems()
        adapter = InventoryAdapter(items)
        rvInventory.adapter = adapter
    }
}
