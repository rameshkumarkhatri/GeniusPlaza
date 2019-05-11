package com.app.androidapp.service


import com.mobifyall.geniusplaza.model.Model
import io.reactivex.Observable
import retrofit2.http.*

interface WebServiceApi {

    @GET("users")
    fun getUsers(@Query("page")  page : Int): Observable<Model.UsersData>;

    @POST("users")
    fun postUser(@Body user : Model.User): Observable<Model.User>;


}

