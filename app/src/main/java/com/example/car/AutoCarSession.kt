package com.example.car

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session

class AutoCarSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return AutoCarMainScreen(carContext)
    }
}
