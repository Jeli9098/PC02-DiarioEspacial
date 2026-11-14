// ============================================================
// app/build.gradle.kts  — SOLO las partes que se AGREGAN al template
// de Android Studio (Empty Activity / Compose). No borres lo demás.
// ============================================================
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")          // <-- AGREGAR (Room)
}

// <-- AGREGAR: leer la API key desde local.properties (no se sube a GitHub)
val localProps = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}

android {
    // namespace, compileSdk, etc. quedan como en el template

    defaultConfig {
        // ... lo del template ...
        buildConfigField(
            "String",
            "NASA_API_KEY",
            "\"${localProps.getProperty("NASA_API_KEY", "")}\""
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true                 // <-- AGREGAR
    }
}

dependencies {
    // ... las del template ...

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.8.4")
    // ViewModel en Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    // Íconos (Favorite, Image, Public...)
    implementation("androidx.compose.material:material-icons-extended")
    // Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    // Imágenes
    implementation("io.coil-kt:coil-compose:2.7.0")
}

// ============================================================
// build.gradle.kts (RAÍZ del proyecto) — agregar en plugins { }:
//
//   id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
//
// La versión de KSP debe empezar igual que tu versión de Kotlin
// (mira libs.versions.toml -> kotlin = "2.0.21"). Si tu Kotlin es otro,
// busca la KSP equivalente en github.com/google/ksp/releases
// ============================================================
