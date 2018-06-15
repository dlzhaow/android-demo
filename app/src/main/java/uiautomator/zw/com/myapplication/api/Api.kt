package uiautomator.zw.com.myapplication.api

import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable
import uiautomator.zw.com.myapplication.model.User
import uiautomator.zw.com.myapplication.api.req.ReqLogin


interface Api{

    @POST("login")
    fun login(@Body reqLogin: ReqLogin): Observable<User>


}