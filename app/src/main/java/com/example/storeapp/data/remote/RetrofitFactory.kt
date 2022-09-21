package com.example.storeapp.data.remote

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.example.storeapp.helpers.C
import com.example.storeapp.model.ApiService
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {

    companion object {

        private const val TAG = "[StoreApp]"

        private var okHttpClient: OkHttpClient? = null

        private fun getOkHttpClient(): OkHttpClient {

            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(provideHeaderHttpLoggingInterceptor())
                .addInterceptor(provideBodyHttpLoggingInterceptor())
                .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return okHttpClient!!
        }

        private var headerHttpLoggingInterceptor: HttpLoggingInterceptor? = null
        private fun provideHeaderHttpLoggingInterceptor(): HttpLoggingInterceptor {

            if (headerHttpLoggingInterceptor == null) {
                headerHttpLoggingInterceptor = HttpLoggingInterceptor { message ->
                    Log.d(TAG, message)
                }
                headerHttpLoggingInterceptor!!.level =
                    HttpLoggingInterceptor.Level.HEADERS
            }
            return headerHttpLoggingInterceptor!!
        }

        private var bodyHttpLoggingInterceptor: HttpLoggingInterceptor? = null
        private fun provideBodyHttpLoggingInterceptor(): HttpLoggingInterceptor {
            if (bodyHttpLoggingInterceptor == null) {
                bodyHttpLoggingInterceptor = HttpLoggingInterceptor { message ->
                    Log.d(TAG, message)
                }
                bodyHttpLoggingInterceptor!!.level =
                    HttpLoggingInterceptor.Level.BODY
            }

            return bodyHttpLoggingInterceptor!!
        }

        private var retrofit: Retrofit? = null
        private fun provideRetrofit(): Retrofit {
            val httpClient = getOkHttpClient()

            val gson = GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(
                    MyResponse::class.java,
                    Deserializer<MyResponse<Any>>()
                )
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(C.BASE_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()

            return retrofit!!
        }

        private var userApiService: ApiService? = null
        fun provideUserApiService(): ApiService {
            userApiService = provideRetrofit().create(ApiService::class.java)
            return userApiService!!
        }


    }
}