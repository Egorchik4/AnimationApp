package com.example.animationapp.domain.usecase

import com.example.animationapp.domain.repository.Repository
import okhttp3.ResponseBody
import javax.inject.Inject

class GetPictureUseCase @Inject constructor(private val repository: Repository) {

	suspend operator fun invoke(): ResponseBody =
		repository.getPicture()
}