package uiautomator.zw.com.myapplication


import android.os.Handler
import android.os.Looper
import java.util.HashMap
import java.util.Timer

class TimerTask private constructor(){

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder{
        var INSTANCE = TimerTask()
    }

    var taskMap : HashMap<String, java.util.TimerTask> = HashMap()
    var handle : Handler = Handler(Looper.getMainLooper())

    private  var timer: Timer? = null

    fun execute(action: Runnable, period: Long, taskKey: String): Unit{
        if (taskMap.containsKey(taskKey)) return;
        if (timer == null)
            timer = Timer()
        var timerTask : java.util.TimerTask = object : java.util.TimerTask(){
            override fun run() {
                handle.post(action)
            }
        }
        taskMap.put(taskKey, timerTask)
        timer?.schedule(timerTask, 0, period)
    }

    fun cancelTask(taskKey: String): Unit{
        if (taskMap.containsKey(taskKey)) {
            taskMap.remove(taskKey)?.cancel()
            if (taskMap.isEmpty()) {
                timer?.cancel()
                timer = null
            }
        }
    }

}