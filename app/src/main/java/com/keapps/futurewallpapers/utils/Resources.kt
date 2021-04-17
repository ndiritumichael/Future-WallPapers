package com.keapps.futurewallpapers.utils

data class Resource<out T>(val status: Statuses,val data:T?,val message:String?){

    companion object{
        fun <T> success(data: T): Resource<T> = Resource(status = Statuses.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Statuses.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Statuses.LOADING, data = data, message = null)
    }

}