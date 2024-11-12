package com.example.mad_3

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews



class TaskWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Perform this loop procedure for each widget
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_task)

            // Get tasks from SharedPreferences
            val sharedPreferences = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)
            val taskList = sharedPreferences.getStringSet("task_list", emptySet())?.toList() ?: emptyList()

            // Set task titles on the widget
            views.setTextViewText(R.id.taskItem1, taskList.getOrNull(0) ?: "No Task")
            views.setTextViewText(R.id.taskItem2, taskList.getOrNull(1) ?: "")
            views.setTextViewText(R.id.taskItem3, taskList.getOrNull(2) ?: "")

            // Set up a PendingIntent to launch the app when the widget is clicked
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widgetTitle, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
