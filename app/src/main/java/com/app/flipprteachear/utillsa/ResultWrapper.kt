package com.hubwallet.utillss

sealed class ResultWrapper<out T: Any> {
	data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
	data class GenericError(val error: ErrorResponse? = null): ResultWrapper<Nothing>()
	object NetworkError: ResultWrapper<Nothing>()
	object Loading : ResultWrapper<Nothing>()
}