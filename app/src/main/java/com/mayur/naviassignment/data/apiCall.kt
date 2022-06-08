package com.mayur.naviassignment.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


typealias NetworkCall<T> = suspend () -> Response<T>

suspend fun <T: Any> apiCall(networkCall: NetworkCall<T>): AsyncResult<T> {
    var result = AsyncResult<T>(resultState = ResultState.IN_PROGRESS)

    withContext(Dispatchers.IO) {
        result = try {
            val response = networkCall()
            if (response.isSuccessful) {
                processResponse(result, response)
            } else processError(result, Throwable(response.errorBody()?.string()))
        } catch (t: Throwable) {
            processError(result, t)
        }
    }

    return result
}

fun <T> processResponse(result: AsyncResult<T>, response: Response<T>): AsyncResult<T> {
    val body = response.body()

    body?.let {
        return result.success(it)
    } ?: run {
        if (response.code() == 200) {
            Log.i(TAG, "response code is 200 but body is null")
        }

        return result.error(SERVER_ERROR_MSG, ResultState.ERROR)
    }
}

fun <T> processError(result: AsyncResult<T>, t: Throwable): AsyncResult<T> {
    Log.i(TAG, t.message.toString())
    t.printStackTrace()
    return result.error(t.message ?: GENERIC_ERROR_MSG, ResultState.ERROR)
}


const val SERVER_ERROR_MSG = "Bad server response"
const val GENERIC_ERROR_MSG = "Something went wrong"
const val TAG = "apiCall"
