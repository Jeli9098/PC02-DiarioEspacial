package com.esan.diarioespacial

import android.app.Application
import com.esan.diarioespacial.data.local.AppDatabase

class DiarioApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
}
