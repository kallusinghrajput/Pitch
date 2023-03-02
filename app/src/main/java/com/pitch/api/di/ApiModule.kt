package com.pitch.api.di

import android.content.Context
import com.pitch.BuildConfig
import com.pitch.R
import com.pitch.api.apiinterface.APIS
import com.pitch.utils.Constant.BASE_URL
import com.pitch.utils.Exception.NoInternetException
import com.pitch.utils.internet.Internet.isInternetAvailable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    fun provideInterceptors(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun providesHttpLoginInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            if (!isInternetAvailable(context))
                throw NoInternetException(context.resources.getString(R.string.checkInternet))
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
            chain.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }


    @Provides
    @Singleton
    fun provideMoshi(): Moshi? {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi?, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi!!).asLenient())
        .build()


    @Provides
    @Singleton
    fun provideApis(retrofit: Retrofit): APIS {
        return retrofit.create(APIS::class.java)
    }


}