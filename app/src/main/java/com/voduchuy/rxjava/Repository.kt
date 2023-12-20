package com.voduchuy.rxjava



class Repository constructor(private val apiInterface: ApiInterface) {
    fun getAll() = apiInterface.getTodo()

}

