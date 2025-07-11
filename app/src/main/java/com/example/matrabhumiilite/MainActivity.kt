package com.example.matrabhumiilite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openCropDiseaseActivity(view: View) {
        val intent = Intent(this, CropDiseaseActivity::class.java)
        startActivity(intent)
    }

    fun openCropCalendarActivity(view: View) {
        val intent = Intent(this, CropCalendarActivity::class.java)
        startActivity(intent)
    }


    fun openMarketPriceActivity(view: View) {
        val intent = Intent(this, MarketPriceActivity::class.java)
        startActivity(intent)
    }

    fun openFarmerNotesActivity(view: View) {
        val intent = Intent(this, FarmerNotesActivity::class.java)
        startActivity(intent)
    }
    fun openFertilizerActivity(view: View) {
        startActivity(Intent(this, FertilizerRecommendationActivity::class.java))
    }

    fun openKrishiFaqActivity(view: View) {
        startActivity(Intent(this, KrishiFaqActivity::class.java))
    }

    fun openProfitCalculatorActivity(view: View) {
        startActivity(Intent(this, ProfitCalculatorActivity::class.java))
    }

    fun openInventoryActivity(view: View) {
        startActivity(Intent(this, InventoryActivity::class.java))
    }
    fun openSchemesActivity(view: View) {
        startActivity(Intent(this, SchemesActivity::class.java))
    }
    fun openSoilHealthActivity(view: View) {
        startActivity(Intent(this, SoilHealthActivity::class.java))
    }
    fun openPestControlActivity(view: View) {
        startActivity(Intent(this, PestControlActivity::class.java))
    }
    fun openOrganicFarming(view: View) {
        startActivity(Intent(this, OrganicFarmingActivity::class.java))
    }
    fun openPestControl(view: View) {
        val intent = Intent(this, PestControlActivity::class.java)
        startActivity(intent)
    }

    fun openSoilHealth(view: View) {
        val intent = Intent(this, SoilHealthActivity::class.java)
        startActivity(intent)
    }


}
