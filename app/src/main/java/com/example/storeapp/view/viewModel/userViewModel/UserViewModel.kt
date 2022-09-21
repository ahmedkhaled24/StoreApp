package com.example.storeapp.view.viewModel.userViewModel

import com.example.storeapp.helpers.BaseObservable
import com.example.storeapp.model.ApiService
import io.reactivex.disposables.CompositeDisposable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


private const val TAG = "appDebug"
class UserViewModel(private val mApiService: ApiService) : BaseObservable() {
    private val mDisposable = CompositeDisposable()


//    private val _loginMutableLiveData = MutableLiveData<Resource<BranchesResponse>>()

//    fun branchesViewM(): MutableLiveData<Resource<BranchesResponse>> {
//        _loginMutableLiveData.value = Resource.loading(true)
//        mApiService.branches()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<BranchesResponse> {
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable.add(d)
//                }
//
//                override fun onSuccess(t: BranchesResponse) {
//                    _loginMutableLiveData.value = Resource.data(t)
//                }
//
//                override fun onError(e: Throwable) {
//                    var errorMessage = C.ERROR_MESSAGE_ENGLISH
//                    when (e) {
//                        is UnknownHostException, is SocketTimeoutException, is IOException -> {
//                            Log.d(TAG, "UserViewModel, onError: UnKnownHostException: ${e.message}")
//                            errorMessage = C.INTERNET_ERROR_MESSAGE_ENGLISH
//                        }
//
//                        is HttpException -> {
//                            val errorBody = e.response()!!.errorBody()
//                            when (e.code()) {
//                                401 -> {
//                                    errorBody?.string()?.let {
//                                        try {
//                                            val jObjectError = JSONObject(it)
//                                            errorMessage = jObjectError.getString("message")
//                                        } catch (e: Exception) {
//                                            Log.d(TAG, "onError: $e")
//                                        }
//                                    }
//                                }
//
//                                422 -> {
//                                    errorBody?.string()?.let {
//                                        try {
//                                            val jObjectError = JSONObject(it)
//                                            errorMessage = jObjectError.getString("message")
//                                        } catch (e: Exception) {
//                                            Log.d(TAG, "onError: $e")
//                                        }
//                                    }
//                                }
//
//                                else -> {
//                                    errorBody?.string()?.let {
//                                        try {
//                                            val jObjError = JSONObject(it).getJSONObject("errors")
//                                            errorMessage = jObjError.getString("message")
//                                        } catch (e: Exception) {
//                                            Log.d(TAG, "UserViewModel, onError: exception: $e")
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        else -> {
//                            Log.d(TAG, "UserViewModel, else: onError: Unknown Error: $e")
//                        }
//                    }
//                    _loginMutableLiveData.value = Resource.error(errorMessage)
//                }
//            })
//        return _loginMutableLiveData
//    }



    override fun onCleared() {
        super.onCleared()
        mDisposable.dispose()
    }
}
