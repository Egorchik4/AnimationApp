package com.example.animationapp.domain.repository

import okhttp3.ResponseBody

interface Repository {

	suspend fun getPicture(): ResponseBody
}