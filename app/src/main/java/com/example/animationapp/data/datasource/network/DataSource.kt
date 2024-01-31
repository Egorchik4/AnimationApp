package com.example.animationapp.data.datasource.network

import com.example.animationapp.data.api.Api
import okhttp3.ResponseBody
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val api: Api) : DataSource {

	override suspend fun getPicture(): ResponseBody =
		api.getPicture()
}

interface DataSource {

	suspend fun getPicture(): ResponseBody
}