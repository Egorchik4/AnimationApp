package com.example.animationapp.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface Api {

	@GET(".")
	suspend fun getPicture(): ResponseBody
}