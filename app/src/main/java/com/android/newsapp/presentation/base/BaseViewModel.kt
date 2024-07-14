package com.android.newsapp.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.newsapp.R
import com.android.newsapp.domain.models.News
import com.android.newsapp.presentation.util.helpers.ConnectivityHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

/**
 * BaseViewModel serves as a foundation for ViewModels handling API calls and managing state.
 *
 * @param application The application context used for resource access and connectivity checks.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Executes an API call asynchronously and updates the provided LiveData with the result.
     *
     * @param liveData The MutableLiveData to update with the API call result.
     * @param apiCall The suspend function representing the API call.
     * @param successHandler Callback to handle successful API response.
     */
    protected suspend fun <T> handleApiCall(
        liveData: MutableLiveData<Resource<News>>,
        apiCall: suspend () -> Response<T>,
        successHandler: (T) -> Unit
    ) {
        // Post loading state to LiveData before executing API call
        liveData.postValue(Resource.Loading())

        try {
            // Check if internet is available before making the API call
            if (ConnectivityHelper.isInternetAvailable(getApplication())) {
                // Perform API call in IO dispatcher for network operations
                val response = withContext(Dispatchers.IO) { apiCall() }

                // Handle successful response
                if (response.isSuccessful) {
                    response.body()?.let { resultResponse ->
                        // Call success handler with API response data
                        successHandler(resultResponse)
                        return
                    }
                        ?: liveData.postValue(Resource.Error(getApplication<Application>().getString(R.string.no_data_available)))
                } else {
                    // Post error state to LiveData with error message from API response
                    liveData.postValue(Resource.Error(response.message()))
                }
            } else {
                // Post error state to LiveData when no internet connection
                liveData.postValue(Resource.Error(getApplication<Application>().getString(R.string.no_internet_connection)))
            }
        } catch (e: IOException) {
            // Post error state to LiveData for network failures
            liveData.postValue(Resource.Error(getApplication<Application>().getString(R.string.network_failure)))
        }
    }
}
