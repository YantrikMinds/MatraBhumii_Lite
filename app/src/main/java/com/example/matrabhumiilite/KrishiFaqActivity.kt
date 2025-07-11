package com.example.matrabhumiilite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.InputStream

class KrishiFaqActivity : AppCompatActivity() {

    private lateinit var rvFaq: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_krishi_faq)

        rvFaq = findViewById(R.id.rvFaq)

        val faqList = mutableListOf<Pair<String, String>>()
        val inputStream: InputStream = assets.open("faq_data.json")
        val json = JSONArray(inputStream.reader().readText())

        for (i in 0 until json.length()) {
            val item = json.getJSONObject(i)
            val question = item.getString("question")
            val answer = item.getString("answer")
            faqList.add(Pair(question, answer))
        }

        rvFaq.layoutManager = LinearLayoutManager(this)
        rvFaq.adapter = KrishiFaqAdapter(faqList)
    }
}
