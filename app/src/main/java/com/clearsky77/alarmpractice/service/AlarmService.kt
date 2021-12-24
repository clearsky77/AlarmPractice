package com.clearsky77.alarmpractice.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.clearsky77.alarmpractice.receiver.AlarmReceiver
import com.clearsky77.alarmpractice.util.Constants
import com.clearsky77.alarmpractice.util.RandomIntUtil

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    fun setExactAlarm(timeInMillis: Long){
        setAlarm(
            timeInMillis,
            getPedingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_EXACT_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
            )
        )
    }

    // 매주(Every week)
    fun setRepetitiveAlarm(timeInMillis: Long){
        setAlarm(
            timeInMillis,
            getPedingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_REPETIITIVE_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
            )
        )
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

    private fun getPedingIntent(intent: Intent) : PendingIntent =
        PendingIntent.getBroadcast(
            context,
            RandomIntUtil().getRandomInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )




}