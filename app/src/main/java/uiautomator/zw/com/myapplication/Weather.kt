package uiautomator.zw.com.myapplication

import com.google.gson.annotations.SerializedName

/**
 * Created by zhaowei on 2018/6/15.
 */
data class Weather(@SerializedName("daily_forecast") val forecast:List<Forecast>){}

//data class Forecast(val date:String,@SerializedName("cond") val more:More,@SerializedName("tmp") val temperature:Temperature){}

//data class Temperature (val max:String,val min:String) {}

data class Forecast(val date:String,@SerializedName("cond") val more:More,@SerializedName("tmp") val temperature:HashMap<String, String>){}


data class More (@SerializedName("txt_d") val info:String){}