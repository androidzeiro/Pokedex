package br.com.raphael.pokedex.data.remote

import android.content.Context
import br.com.raphael.pokedex.R
import br.com.raphael.pokedex.extension.isInternetAvailable
import br.com.raphael.pokedex.data.model.dto.Result
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response
import timber.log.Timber

object FlowApiCall {
    private const val TAG_RESULT = "getResult()"

    suspend fun <T> getResult(
        context: Context,
        flowCollector: FlowCollector<Result<T>>,
        request: suspend () -> Response<T>
    ) {
        if (context.isInternetAvailable()) {
            try {
                val response = request()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    flowCollector.emit(Result.Success(body, response.code()))
                } else {
                    val errorMessage = when (response.code()) {
                        401 -> context.getString(R.string.error_unauthorized_message)
                        404 -> context.getString(R.string.error_not_found)
                        else -> context.getString(R.string.error_generic_message)
                    }
                    flowCollector.emit(Result.Error(errorMessage, response.code()))
                }
            } catch (exception: Exception) {
                Timber.tag(TAG_RESULT).e(exception)
                flowCollector.emit(Result.Error(context.getString(R.string.error_generic_message), 0))
            }
        } else {
            flowCollector.emit(Result.NetworkError(context.getString(R.string.error_network)))
        }
    }
}