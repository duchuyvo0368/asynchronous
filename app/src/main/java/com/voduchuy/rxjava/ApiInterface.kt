package com.voduchuy.rxjava

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("todos/1")
    fun getTodo(): Observable<Todo>
}