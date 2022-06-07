package com.mayur.naviassignment.data

data class AsyncResult<T>(
    val resultState: ResultState = ResultState.NOT_STARTED,
    val errorMessage: String? = null,
    val error: Throwable? = null,
    val result: T? = null,
) {

    fun isSuccess() = resultState == ResultState.SUCCESS
    fun isSuccessNonNull() = resultState == ResultState.SUCCESS && result != null
    fun isError() = resultState == ResultState.ERROR
    fun inProgress() = resultState == ResultState.IN_PROGRESS


    fun success(result: T): AsyncResult<T> {
        return copy(resultState = ResultState.SUCCESS, result = result)
    }

    fun error(message: String?, resultState: ResultState = ResultState.ERROR): AsyncResult<T> {
        return copy(resultState = resultState, errorMessage = message)
    }

    fun error(
        t: Throwable?,
        message: String?,
        resultState: ResultState = ResultState.ERROR
    ): AsyncResult<T> {
        return copy(resultState = resultState, error = t, errorMessage = message)
    }

    fun errorMessage(default: String): String {
        return errorMessage ?: default
    }

}

enum class ResultState {
    NOT_STARTED, IN_PROGRESS, SUCCESS, ERROR
}