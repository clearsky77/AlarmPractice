package com.clearsky77.alarmpractice.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.clearsky77.alarmpractice.receiver.AlarmReceiver
import com.clearsky77.alarmpractice.util.RandomIntUtil

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    fun setExactAlarm(timeInMillis: Long){

    }

    // 매주(Every week)
    fun setRepetitveAlarm(timeInMillis: Long){

    }

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
        alarmManager?.let {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }else{
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    private fun getIntent() : Intent = Intent(context, AlarmReceiver::class.java)

    private fun getPedingIntent(intent: Intent) : PendingIntent! =
        PendingIntent.getBroadcast(
            context,
            RandomIntUtil().getRandomInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )




}