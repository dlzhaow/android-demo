package uiautomator.zw.com.myapplication.api


import com.google.gson.*
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory


class ApiGenerator {


    companion object {
        val BASE_URL = "http://localhost:8080/app/"

        var retrofit: Retrofit? = null

        fun <S> createService(serviceClass: Class<S>): S {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder()
                                .addInterceptor(HttpLoggingInterceptor()
                                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                                .build())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
            }
            return retrofit!!.create(serviceClass)
        }
    }
}
