package com.example.storeapp.view.viewModel.userViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.storeapp.helpers.BaseObservable
import com.example.storeapp.helpers.C
import com.example.storeapp.helpers.Resource
import com.example.storeapp.model.ApiService
import com.example.storeapp.model.response.ProductsResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


private const val TAG = "appDebug"
class UserViewModel(private val mApiService: ApiService) : BaseObservable() {
    private val mDisposable = CompositeDisposable()


    private val _productsMutableLiveData = MutableLiveData<Resource<List<ProductsResponse>>>()

    fun productsViewM(): MutableLiveData<Resource<List<ProductsResponse>>> {
        _productsMutableLiveData.value = Resource.loading(true)
        mApiService.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ProductsResponse>> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable.add(d)
                }

                override fun onSuccess(t: List<ProductsResponse>) {
                    _productsMutableLiveData.value = Resource.data(t)
                }

                override fun onError(e: Throwable) {
                    var errorMessage = C.ERROR_MESSAGE_ENGLISH
                    when (e) {
                        is UnknownHostException, is SocketTimeoutException, is IOException -> {
                            Log.d(TAG, "UserViewModel, onError: UnKnownHostException: ${e.message}")
                            errorMessage = C.INTERNET_ERROR_MESSAGE_ENGLISH
                        }

                        is HttpException -> {
                            val errorBody = e.response()!!.errorBody()
                            when (e.code()) {
                                401 -> {
                                    errorBody?.string()?.let {
                                        try {
                                            val jObjectError = JSONObject(it)
                                            errorMessage = jObjectError.getString("message")
                                        } catch (e: Exception) {
                                            Log.d(TAG, "onError: $e")
                                        }
                                    }
                                }

                                422 -> {
                                    errorBody?.string()?.let {
                                        try {
                                            val jObjectError = JSONObject(it)
                                            errorMessage = jObjectError.getString("message")
                                        } catch (e: Exception) {
                                            Log.d(TAG, "onError: $e")
                                        }
                                    }
                                }

                                else -> {
                                    errorBody?.string()?.let {
                                        try {
                                            val jObjError = JSONObject(it).getJSONObject("errors")
                                            errorMessage = jObjError.getString("message")
                                        } catch (e: Exception) {
                                            Log.d(TAG, "UserViewModel, onError: exception: $e")
                                        }
                                    }
                                }
                            }
                        }
                        else -> {
                            Log.d(TAG, "UserViewModel, else: onError: Unknown Error: $e")
                        }
                    }
                    _productsMutableLiveData.value = Resource.error(errorMessage)
                }
            })
        return _productsMutableLiveData
    }



    override fun onCleared() {
        super.onCleared()
        mDisposable.dispose()
    }
}
