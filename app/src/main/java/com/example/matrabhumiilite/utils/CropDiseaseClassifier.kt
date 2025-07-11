package com.example.matrabhumiilite.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class CropDiseaseClassifier(private val context: Context) {

    private val interpreter: Interpreter
    private val labels: List<String>

    companion object {
        private const val MODEL_NAME = "plant_disease_model.tflite"
        private const val LABELS_FILE = "labels.txt"
        private const val IMAGE_SIZE = 200  // ✅ FIXED from 224 → 200
        private const val TAG = "Classifier"
    }

    init {
        interpreter = Interpreter(loadModelFile())
        labels = loadLabels()
        Log.d(TAG, "Labels loaded: ${labels.size}")
        Log.d(TAG, "Input tensor shape: " + interpreter.getInputTensor(0).shape().joinToString())
        Log.d(TAG, "Input tensor type: " + interpreter.getInputTensor(0).dataType())
    }

    private fun loadModelFile(): ByteBuffer {
        val assetFileDescriptor = context.assets.openFd(MODEL_NAME)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabels(): List<String> {
        return try {
            context.assets.open(LABELS_FILE).bufferedReader().readLines().filter { it.isNotBlank() }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading labels: ${e.message}")
            emptyList()
        }
    }

    fun classify(bitmap: Bitmap): String {
        Log.d(TAG, "Starting classification")

        if (labels.isEmpty()) {
            Log.e(TAG, "No labels found!")
            return "Error: No labels found!"
        }

        val resized = Bitmap.createScaledBitmap(bitmap, IMAGE_SIZE, IMAGE_SIZE, true)
        Log.d(TAG, "Bitmap resized")

        val input = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3)
        input.order(ByteOrder.nativeOrder())
        Log.d(TAG, "ByteBuffer created")

        for (y in 0 until IMAGE_SIZE) {
            for (x in 0 until IMAGE_SIZE) {
                val pixel = resized.getPixel(x, y)
                input.putFloat((pixel shr 16 and 0xFF) / 255.0f)
                input.putFloat((pixel shr 8 and 0xFF) / 255.0f)
                input.putFloat((pixel and 0xFF) / 255.0f)
            }
        }
        Log.d(TAG, "Bitmap converted to input tensor")

        val output = Array(1) { FloatArray(labels.size) }
        Log.d(TAG, "Output array created")

        try {
            interpreter.run(input, output)
            Log.d(TAG, "Model inference done")
        } catch (e: Exception) {
            Log.e(TAG, "Model run failed: ${e.message}")
            return "Model run failed"
        }

        val maxIdx = output[0].indices.maxByOrNull { output[0][it] } ?: -1
        if (maxIdx == -1) {
            Log.e(TAG, "Could not find max index!")
            return "Prediction failed!"
        }

        val confidence = output[0][maxIdx] * 100
        Log.d(TAG, "Max index = $maxIdx | Confidence = $confidence")

        return "${labels[maxIdx]} (%.2f%% confidence)".format(confidence)
    }
}
