package com.example.animationapp.di

import com.example.animationapp.data.api.Api
import com.example.animationapp.data.datasource.network.DataSource
import com.example.animationapp.data.datasource.network.DataSourceImpl
import com.example.animationapp.data.datasource.retrofit.RetrofitBuilder
import com.example.animationapp.data.repository.RepositoryImpl
import com.example.animationapp.domain.repository.Repository
import com.example.animationapp.domain.usecase.GetPictureUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

	@Provides
	@Singleton
	fun provideApi(): Api {
		return RetrofitBuilder().api
	}

	@Provides
	@Singleton
	fun provideDataSource(api: Api): DataSource {
		return DataSourceImpl(api)
	}

	@Provides
	@Singleton
	fun provideRepository(dataSource: DataSource): Repository {
		return RepositoryImpl(dataSource)
	}

	@Provides
	@Singleton
	fun provideGetPictureUseCase(repository: Repository): GetPictureUseCase {
		return GetPictureUseCase(repository)
	}
}