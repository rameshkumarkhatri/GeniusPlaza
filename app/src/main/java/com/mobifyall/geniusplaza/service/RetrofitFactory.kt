package com.app.androidapp.service

import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {


    companion object {


        fun create(): WebServiceApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://reqres.in/api/")
                .build()

            return retrofit.create(WebServiceApi::class.java)
        }



    }

    val WebServiceApi by lazy {
        create()
    }
    var disposable: Disposable? = null


}