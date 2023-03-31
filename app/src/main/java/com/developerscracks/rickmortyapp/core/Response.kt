package com.developerscracks.rickmortyapp.core

sealed class Response<out R>{
    data class Success<T>(val data: T): Response<T>()
    data class Error<out T>(val exception: Exception?): Response<T>()
}