package com.example.car

import android.content.Intent
import android.net.Uri
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import com.example.MainActivity

class AutoCarMainScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val listBuilder = ItemList.Builder()

        listBuilder.addItem(
            Row.Builder()
                .setTitle("Google Search")
                .addText("Hands-free web search for quick answers")
                .setOnClickListener {
                    openUrl("https://www.google.com")
                }
                .build()
        )

        listBuilder.addItem(
            Row.Builder()
                .setTitle("AccuWeather Forecast")
                .addText("Live radar and regional travel weather")
                .setOnClickListener {
                    openUrl("https://www.accuweather.com")
                }
                .build()
        )

        listBuilder.addItem(
            Row.Builder()
                .setTitle("NPR News / Live Audio")
                .addText("Hourly news bulletins and live radio streams")
                .setOnClickListener {
                    openUrl("https://www.npr.org")
                }
                .build()
        )

        listBuilder.addItem(
            Row.Builder()
                .setTitle("Wikipedia Portal")
                .addText("Quick reference information")
                .setOnClickListener {
                    openUrl("https://en.wikipedia.org")
                }
                .build()
        )

        listBuilder.addItem(
            Row.Builder()
                .setTitle("Open Browser on Device")
                .addText("Launch full AutoWeb browser window on phone")
                .setOnClickListener {
                    launchMainApp()
                }
                .build()
        )

        val actionStrip = ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setTitle("Launch")
                    .setOnClickListener {
                        launchMainApp()
                    }
                    .build()
            )
            .build()

        return ListTemplate.Builder()
            .setTitle("AutoWeb Browser")
            .setHeaderAction(Action.APP_ICON)
            .setSingleList(listBuilder.build())
            .setActionStrip(actionStrip)
            .build()
    }

    private fun openUrl(url: String) {
        CarToast.makeText(carContext, "Loading on AutoWeb...", CarToast.LENGTH_SHORT).show()
        try {
            val intent = Intent(carContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                data = Uri.parse(url)
            }
            carContext.startActivity(intent)
        } catch (e: Exception) {
            // Intent start fallback
        }
    }

    private fun launchMainApp() {
        try {
            val intent = Intent(carContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            carContext.startActivity(intent)
            CarToast.makeText(carContext, "Browser opened on device", CarToast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // Intent start fallback
        }
    }
}
