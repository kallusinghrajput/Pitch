package com.pitch.api.apistate

sealed class Result<T>(val data: T? = null, val message: String = "", val errorData: T? = null) {
    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(errorMessage: String, dataError: T) :
        Result<T>(message = errorMessage, errorData = dataError)
    class Loading<T> : Result<T>()
}