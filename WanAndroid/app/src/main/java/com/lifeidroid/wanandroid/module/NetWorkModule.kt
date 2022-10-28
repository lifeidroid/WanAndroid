package com.lifeidroid.wanandroid.module

import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.lifeidroid.wanandroid.App
import com.lifeidroid.wanandroid.BuildConfig
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.api.HttpApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * 创建日期：2021/9/28 10:18 下午
 * @author LiFei
 * @version 1.0
 * 包名： com.carl.jwt.module
 * 类说明：Http模块
 */
@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val cookieJar =
            PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.instance))
        builder.cookieJar(cookieJar)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Log.d("CarlHttp", message)
            }
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

//    /**
//     * 接收拦截器，获取Cookie
//     */
//    public class ReceivedCookiesInterceptor :Interceptor{
//        override fun intercept(chain: Interceptor.Chain): Response {
//            var originalResponse = chain.proceed(chain.request())
//            if (!originalResponse.headers("Set-Cookie").isEmpty()){
//                //解析Cookie
//                for (header in originalResponse.headers("Set-Cookie")){
//                    if (header.contains("JSESSIONID")){
////                        NetClient.COOKIE = header.substring(header.indexOf("JSESSIONID"),header.indexOf(";"))
//                    }
//                }
//            }
//            return originalResponse
//        }
//
//    }
//
//    public class AddCookiesInterceptor :Interceptor{
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val builder = chain.request().newBuilder()
//            //添加Cookie
//            if ()
//        }
//
//    }

    @Singleton
    @Provides
    fun provideHttpApi(okHttpClient: OkHttpClient): HttpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build().create(HttpApi::class.java)
    }

}