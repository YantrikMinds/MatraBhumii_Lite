🌾 MatraBhumi Lite – A Smart Offline App for Indian Farmers 🇮🇳
MatraBhumi Lite is a completely offline, lightweight, and multi-featured Android app designed specially for rural Indian farmers. It helps farmers stay informed, detect crop diseases, manage notes, and get access to mandi prices and government schemes — all without needing internet.

📱 Built with Kotlin + Android Studio
🎯 Target: Android 7.0+ (Nougat and above)
🌐 Language: Hindi + Hinglish
🪶 App Size: < 30 MB (fully offline)



🚜 Features
🌱 Crop Disease Detection (AI + TFLite)

Detect plant diseases using camera or gallery image.

Works completely offline using a lightweight TensorFlow Lite model.

Shows: Disease name, causes, and precautions.



📅 Crop Calendar

Month-wise cropping schedule for major Indian crops.

Zone-specific info: North, South, East, West, Central India.

Offline data; no need for net.


🏛️ Govt Schemes (सरकारी योजनाएं)

100+ major central & state government schemes in Hindi.

Stored offline using lightweight JSON.

Easy scroll + search interface.


📊 Mandi Prices (मंडी भाव)

Preloaded mandi rates (chart + list format).

Covers major crops: Wheat, Rice, Bajra, Sugarcane, etc.

Zone-wise comparison with images.


📝 Farmer Notes (किसान डायरी)

Farmers can write and save their own personal notes.

SQLite based offline storage.

Add/Edit/Delete notes, sorted by date.

Voice command for navigation (optional).

Works offline using VOSK for Speech-to-Text and Android TTS.

This can be enabled in future builds.
AND A LOT MORE FEATURES





🧱 Tech Stack
Kotlin + Android Studio (XML + Compose hybrid)

TFLite for ML Model

SQLite for offline data storage

ViewBinding + Jetpack Navigation

JSON for static data (Schemes, Mandi Rates)

AND A LOT MOREE TO EXPLORE....

📁 Project Structure
swift
Copy
Edit
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/matrabhumi/app/
│   │   │   ├── res/layout/        → All XML UI files
│   │   │   ├── assets/            → plant_disease_model.tflite, labels.txt
│   │   │   ├── res/raw/           → Optional audio files
│   │   │   ├── res/drawable/      → App icons and images
│   │   │   └── res/values/        → strings.xml, colors.xml
│   │   └── AndroidManifest.xml
🧠 AI Model Info
Model: plant_disease_model.tflite

Classes: 100+ diseases across 80+ Indian crops

Input: 224x224 image

Output: Disease label with highest confidence

Labels file: labels.txt in assets

💡 Motivation
MatraBhumi Lite was created to empower rural farmers with knowledge and tools that usually require internet, and to bring AI to the grassroots level — all in their own language.

