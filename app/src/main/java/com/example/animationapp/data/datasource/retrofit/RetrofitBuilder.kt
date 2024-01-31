package com.example.animationapp.data.datasource.retrofit

import com.example.animationapp.data.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitBuilder @Inject constructor() {

	private val url: String = "https://loremflickr.com/640/360/"
	private val retrofit: Retrofit = Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.baseUrl(url)
		.build()

	val api: Api = retrofit.create(Api::class.java)
}