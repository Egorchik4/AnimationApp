package com.example.animationapp.presentation.state

import android.graphics.Bitmap

sealed class AnimationState {
	data object Initial : AnimationState()

	data object ContentSpin : AnimationState()

	data class ContentPicture(val bitmap: Bitmap) : AnimationState()

	data class ContentText(val text: String) : AnimationState()

	data class Error(val errorMessage: String) : AnimationState()
}