package com.example.matrabhumiilite
import com.example.matrabhumiilite.utils.CropDiseaseClassifier

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class CropDiseaseActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var resultText: TextView
    private lateinit var classifier: CropDiseaseClassifier
    private var selectedImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_disease)

        imageView = findViewById(R.id.imageView)
        resultText = findViewById(R.id.resultText)
        val btnSelect = findViewById<Button>(R.id.btnSelect)
        val btnPredict = findViewById<Button>(R.id.btnPredict)

        // Load TFLite model
        classifier = CropDiseaseClassifier(this)

        // Select image from gallery
        btnSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePickerLauncher.launch(intent)
        }

        // Predict disease
        btnPredict.setOnClickListener {
            selectedImage?.let {
                val result = classifier.classify(it)
                resultText.text = result
            }
        }
    }

    // Image picker result
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                uri?.let {
                    selectedImage = getBitmapFromUri(it)
                    imageView.setImageBitmap(selectedImage)
                }
            }
        }

    // Convert URI to Bitmap
    // Convert URI to compatible Bitmap
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            // ❗ FIX: Convert from HARDWARE to mutable ARGB_8888
            bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }
    }

}
