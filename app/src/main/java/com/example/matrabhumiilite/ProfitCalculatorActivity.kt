package com.example.matrabhumiilite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfitCalculatorActivity : AppCompatActivity() {

    private lateinit var btnCalc: Button
    private lateinit var etCropArea: EditText
    private lateinit var etYield: EditText
    private lateinit var etPrice: EditText
    private lateinit var tvProfit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profit_calculator)

        btnCalc = findViewById(R.id.btnCalc)
        etCropArea = findViewById(R.id.etCropArea)
        etYield = findViewById(R.id.etYield)
        etPrice = findViewById(R.id.etPrice)
        tvProfit = findViewById(R.id.tvProfit)

        btnCalc.setOnClickListener {
            val area = etCropArea.text.toString().toDoubleOrNull()
            val yield = etYield.text.toString().toDoubleOrNull()
            val price = etPrice.text.toString().toDoubleOrNull()

            if (area != null && yield != null && price != null) {
                val profit = area * yield * price
                tvProfit.text = "Estimated Profit: ₹$profit"
            } else {
                tvProfit.text = "Please enter all values"
            }
        }
    }
}

