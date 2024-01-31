package com.example.animationapp.data.repository

import com.example.animationapp.data.datasource.network.DataSource
import com.example.animationapp.domain.repository.Repository
import okhttp3.ResponseBody
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource) : Repository {

	override suspend fun getPicture(): ResponseBody =
		dataSource.getPicture()
}